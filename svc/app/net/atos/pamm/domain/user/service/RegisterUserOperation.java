package net.atos.pamm.domain.user.service;

import com.fasterxml.jackson.databind.JsonNode;
import net.atos.pamm.domain.ServiceResult;
import net.atos.pamm.domain.user.repository.UserRepository;
import net.atos.pamm.domain.user.model.User;
import net.atos.pamm.infrastructure.mail.EmailService;
import net.atos.pamm.infrastructure.security.cipher.BCryptCipher;
import net.atos.pamm.infrastructure.util.RandomKeyGenerator;
import net.atos.pamm.infrastructure.util.RequestUtil;
import play.Logger;
import play.libs.Json;

import javax.inject.Inject;

public class RegisterUserOperation {
    private static final Logger.ALogger LOG = Logger.of(RegisterUserOperation.class);

    private final UserRepository repository;
    private final BCryptCipher cipher;
    private final RandomKeyGenerator randomKeyGenerator;
    private final EmailService emailService;
    private final RequestUtil requestUtil;

    @Inject
    public RegisterUserOperation(UserRepository repository,
                                 BCryptCipher cipher,
                                 RandomKeyGenerator randomKeyGenerator,
                                 EmailService emailService,
                                 RequestUtil requestUtil) {
        this.repository = repository;
        this.cipher = cipher;
        this.randomKeyGenerator = randomKeyGenerator;
        this.emailService = emailService;
        this.requestUtil = requestUtil;
    }

    public ServiceResult execute(JsonNode jsonRequest) {
        final User existingUser = repository.findUserByEmail(jsonRequest.findPath("email").textValue());
        if (existingUser != null) {
            return new ServiceResult(ServiceResult.Status.OP_ERROR);
        } else {
            final User user = Json.fromJson(jsonRequest, User.class);

            user.setPassword(cipher.hash(jsonRequest.findPath("password").textValue()));
            user.setActivated(false);
            user.setActivationKey(randomKeyGenerator.generate());
            user.setRole(User.Role.USER);

            repository.set(user);

            emailService.sendEmail(user.getEmail(), "Please activate your account",
                    views.html.mailtemplates.userActivation.render(user, requestUtil.getBaseUrl()).toString());

            return new ServiceResult(jsonRequest);
        }
    }
}
