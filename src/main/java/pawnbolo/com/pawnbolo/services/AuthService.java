package pawnbolo.com.pawnbolo.services;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pawnbolo.com.pawnbolo.models.*;
import pawnbolo.com.pawnbolo.repositories.PawnStoreRepository;
import pawnbolo.com.pawnbolo.repositories.UserRepository;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {
    private final AWSCognitoIdentityProvider cognitoClient;
    private final UserRepository userRepository;
    private final PawnStoreRepository pawnStoreRepository;

    @Value("${aws.cognito.userPoolId}")
    private String userPoolId;

    @Value("${aws.cognito.clientId}")
    private String clientId;

    @Value("${aws.cognito.clientSecret}")
    private String clientSecret;

    public AuthService(AWSCognitoIdentityProvider cognitoClient, UserRepository userRepository, PawnStoreRepository pawnStoreRepository) {
        this.cognitoClient = cognitoClient;
        this.userRepository = userRepository;
        this.pawnStoreRepository = pawnStoreRepository;
    }

    // Compute the SECRET_HASH for Cognito requests
    private String calculateSecretHash(String username) {
        try {
            String message = username + clientId;
            SecretKeySpec signingKey = new SecretKeySpec(clientSecret.getBytes("UTF-8"), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(rawHmac);
        } catch (Exception e) {
            throw new RuntimeException("Error while calculating secret hash", e);
        }
    }

    // Sign Up User (Cognito + Save in Database)
    public void signUp(String email, String password, String name, Long pawnStoreId) {
        // Retrieve PawnStore
        Optional<PawnStore> pawnStoreOpt = pawnStoreRepository.findById(pawnStoreId);
        if (pawnStoreOpt.isEmpty()) {
            throw new RuntimeException("PawnStore not found with ID: " + pawnStoreId);
        }
        PawnStore pawnStore = pawnStoreOpt.get();

        // Register with Cognito
        Map<String, String> attributes = new HashMap<>();
        attributes.put("email", email);

        SignUpRequest signUpRequest = new SignUpRequest()
                .withClientId(clientId)
                .withUsername(email)
                .withPassword(password)
                .withUserAttributes(
                        new AttributeType().withName("email").withValue(email)
                )
                .withSecretHash(calculateSecretHash(email));

        com.amazonaws.services.cognitoidp.model.SignUpResult result = cognitoClient.signUp(signUpRequest);
        String cognitoUserId = result.getUserSub(); // Retrieve Cognito User ID

        // Save user in the database
        User user = new User();
        user.setEmail(email);
        user.setCognitoUserId(cognitoUserId);
        user.setPawnStore(pawnStore);
        user.setName(name);
        user.setRole(Role.EMPLOYEE); // Default to EMPLOYEE

        userRepository.save(user);
    }
}
