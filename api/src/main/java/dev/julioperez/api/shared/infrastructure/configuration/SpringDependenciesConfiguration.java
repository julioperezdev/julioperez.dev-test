package dev.julioperez.api.shared.infrastructure.configuration;

import dev.julioperez.api.auth.application.createVerificationToken.adapter.CreateVerificationTokenAdapterRepository;
import dev.julioperez.api.auth.application.createVerificationToken.service.CreateVerificationTokenService;
import dev.julioperez.api.auth.application.enableUser.adapter.EnableUserAdapterRepository;
import dev.julioperez.api.auth.application.enableUser.delivery.EnableUserDelivery;
import dev.julioperez.api.auth.application.enableUser.service.EnableUserService;
import dev.julioperez.api.auth.application.login.adapter.LoginAdapterRepository;
import dev.julioperez.api.auth.application.login.adapter.LoginAdapterSecurity;
import dev.julioperez.api.auth.application.login.delivery.LoginDelivery;
import dev.julioperez.api.auth.application.login.service.LoginService;
import dev.julioperez.api.auth.application.modelMapper.AuthenticationResponseModelMapper;
import dev.julioperez.api.auth.application.modelMapper.RefreshTokenModelMapper;
import dev.julioperez.api.auth.application.modelMapper.UserModelMapper;
import dev.julioperez.api.auth.application.modelMapper.VerificationTokenModelMapper;
import dev.julioperez.api.auth.application.refreshToken.adapter.RefreshTokenAdapterRepository;
import dev.julioperez.api.auth.application.refreshToken.adapter.RefreshTokenAdapterSecurity;
import dev.julioperez.api.auth.application.refreshToken.delivery.RefreshTokenDelivery;
import dev.julioperez.api.auth.application.refreshToken.service.RefreshTokenService;
import dev.julioperez.api.auth.application.signup.adapter.SignupAdapterRepository;
import dev.julioperez.api.auth.application.signup.delivery.SignupDelivery;
import dev.julioperez.api.auth.application.signup.service.SignupService;
import dev.julioperez.api.auth.application.validateToken.adapter.ValidateTokenSecurityAdapter;
import dev.julioperez.api.auth.application.validateToken.service.ValidateTokenService;
import dev.julioperez.api.auth.infrastructure.app.security.JwtAuthenticationFilter;
import dev.julioperez.api.auth.infrastructure.app.security.JwtProvider;
import dev.julioperez.api.auth.infrastructure.app.security.ManagerAuthenticator;
import dev.julioperez.api.auth.infrastructure.repository.dao.RefreshTokenDao;
import dev.julioperez.api.auth.infrastructure.repository.dao.UserDao;
import dev.julioperez.api.auth.infrastructure.repository.dao.UserRolDao;
import dev.julioperez.api.auth.infrastructure.repository.dao.VerificationTokenDao;
import dev.julioperez.api.certificate.application.createQrValidator.adapter.CreateQrValidatorAdapterQrGenerator;
import dev.julioperez.api.certificate.application.createQrValidator.service.CreateQrValidatorService;
import dev.julioperez.api.certificate.application.createStudentCertificate.adapter.CreateStudentCertificateAdapterRepository;
import dev.julioperez.api.certificate.application.createStudentCertificate.delivery.CreateStudentCertificateDelivery;
import dev.julioperez.api.certificate.application.createStudentCertificate.service.CreateStudentCertificateService;
import dev.julioperez.api.certificate.application.generateCertificate.adapter.GenerateCertificateAdapterPdfGenerator;
import dev.julioperez.api.certificate.application.generateCertificate.service.GenerateCertificateService;
import dev.julioperez.api.certificate.application.modelMapper.StudentCertificateModelMapper;
import dev.julioperez.api.certificate.infrastructure.gateway.iTextRenderPdf.ITextRenderPdfContract;
import dev.julioperez.api.certificate.infrastructure.gateway.zxingQR.ZxingQrContract;
import dev.julioperez.api.certificate.infrastructure.repository.dao.StudentCertificateDao;
import dev.julioperez.api.emailNotifier.application.sendValidateUserEmail.adapter.SendValidateUserEmailAdapter;
import dev.julioperez.api.emailNotifier.application.sendValidateUserEmail.service.SendValidateUserEmailService;
import dev.julioperez.api.emailNotifier.infrastructure.Gateway.SpringJavaMailer;
import dev.julioperez.api.shared.application.encodeString.adapter.StringEncoderAdapter;
import dev.julioperez.api.shared.application.encodeString.service.StringEncoderService;
import dev.julioperez.api.shared.infrastructure.gateway.SpringStringEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebSecurity
@Configuration
@EnableJpaRepositories(basePackages = "dev.julioperez.api.*")
@EntityScan(basePackages = "dev.julioperez.api.*")
@EnableTransactionManagement
@EnableAutoConfiguration
@ComponentScan(basePackages = {"dev.julioperez.api.*"})
public class SpringDependenciesConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${allowed.origins}")
    private String allowedOriginsUrl;
    //Auth
    private final UserDao userDao;
    private final UserRolDao userRolDao;
    private final VerificationTokenDao verificationTokenDao;
    private final RefreshTokenDao refreshTokenDao;
    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtProvider jwtProvider;
    //certificate
    private final StudentCertificateDao studentCertificateDao;
    private final ITextRenderPdfContract iTextRenderPdfContract;
    private final ZxingQrContract zxingQrContract;
    //emailNotifier
    private final SpringJavaMailer springJavaMailer;

    public SpringDependenciesConfiguration(UserDao userDao, UserRolDao userRolDao, VerificationTokenDao verificationTokenDao, RefreshTokenDao refreshTokenDao, UserDetailsService userDetailsService, JwtAuthenticationFilter jwtAuthenticationFilter, JwtProvider jwtProvider, StudentCertificateDao studentCertificateDao, ITextRenderPdfContract iTextRenderPdfContract, ZxingQrContract zxingQrContract, SpringJavaMailer springJavaMailer) {
        this.userDao = userDao;
        this.userRolDao = userRolDao;
        this.verificationTokenDao = verificationTokenDao;
        this.refreshTokenDao = refreshTokenDao;
        this.userDetailsService = userDetailsService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.jwtProvider = jwtProvider;
        this.studentCertificateDao = studentCertificateDao;
        this.iTextRenderPdfContract = iTextRenderPdfContract;
        this.zxingQrContract = zxingQrContract;
        this.springJavaMailer = springJavaMailer;
    }

    /**
     * Auth/Application/ModelMapper
     */

    @Bean
    public UserModelMapper userModelMapper(){
        return new UserModelMapper();
    }
    @Bean
    public VerificationTokenModelMapper verificationTokenModelMapper(){
        return new VerificationTokenModelMapper();
    }

    @Bean
    RefreshTokenModelMapper refreshTokenModelMapper(){
        return new RefreshTokenModelMapper();
    }

    @Bean
    AuthenticationResponseModelMapper authenticationResponseModelMapper(){
        return new AuthenticationResponseModelMapper();
    }

    /**
     * Auth/Application/signup
     */

    @Bean
    public SignupAdapterRepository signupAdapterRepository(){
        return new SignupAdapterRepository(
                userDao,
                userRolDao,
                userModelMapper());
    }

    @Bean
    public SignupService signupService(){
        return new SignupService(
                signupAdapterRepository(),
                createVerificationTokenService(),
                sendValidateUserEmailService(),
                stringEncoderService());
//                signupModelMapper(),
//                mailSenderServiceImplementation(),
//                mailModelMapper());
    }

    @Bean
    public SignupDelivery signupDelivery(){
        return new SignupDelivery(
                signupService(),
                userModelMapper());
    }

    /**
     * Auth/Application/createVerificationToken
     */

    @Bean
    public CreateVerificationTokenAdapterRepository createVerificationTokenAdapterRepository(){
        return new CreateVerificationTokenAdapterRepository(
                verificationTokenModelMapper(),
                verificationTokenDao,
                userDao);
    }

    @Bean
    public CreateVerificationTokenService createVerificationTokenService(){
        return new CreateVerificationTokenService(
                createVerificationTokenAdapterRepository(),
                verificationTokenModelMapper());
    }




//    @Bean
//    public CreateVerificationTokenDelivery createVerificationTokenDelivery(){
//        return new SignupDelivery(
//                signupService(),
//                userModelMapper());
//    }

    /**
     * Auth/Application/createVerificationToken
     */

    @Bean
    public ValidateTokenSecurityAdapter validateTokenSecurityAdapter(){
        return new ValidateTokenSecurityAdapter(
                jwtProvider);
    }

    @Bean
    public ValidateTokenService validateTokenService(){
        return new ValidateTokenService(
                validateTokenSecurityAdapter());
    }

    /**
     * Auth/Application/login
     */


    @Bean
    public LoginAdapterSecurity loginAdapterSecurity() throws Exception {
        return new LoginAdapterSecurity(
                managerAuthenticator(),
                jwtProvider);
    }

    @Bean
    public LoginAdapterRepository loginAdapterRepository(){
        return new LoginAdapterRepository(userDao);
    }

    @Bean
    public LoginService loginService() throws Exception {
        return new LoginService(
                loginAdapterRepository(),
                loginAdapterSecurity(),
                authenticationResponseModelMapper());
    }

    @Bean
    public LoginDelivery loginDelivery() throws Exception {
        return new LoginDelivery(
                loginService());
    }

    /**
     * Auth/Application/enableUser
     */


    @Bean
    public EnableUserAdapterRepository enableUserAdapterRepository(){
        return new EnableUserAdapterRepository(
                userDao,
                verificationTokenDao,
                userModelMapper());
    }

    @Bean
    public EnableUserService enableUserService(){
        return new EnableUserService(
                enableUserAdapterRepository());
    }

    @Bean
    public EnableUserDelivery enableUserDelivery(){
        return new EnableUserDelivery(
                enableUserService());
    }


    /**
     * Auth/Application/refreshToken
     */

    @Bean
    public RefreshTokenAdapterRepository refreshTokenAdapterRepository(){
        return new RefreshTokenAdapterRepository(
                refreshTokenDao,
                refreshTokenModelMapper());
    }

    @Bean
    public RefreshTokenAdapterSecurity refreshTokenAdapterSecurity(){
        return new RefreshTokenAdapterSecurity(
                jwtProvider);
    }

    @Bean
    public RefreshTokenService refreshTokenService(){
        return new RefreshTokenService(
                refreshTokenModelMapper(),
                refreshTokenAdapterRepository(),
                refreshTokenAdapterSecurity(),
                authenticationResponseModelMapper(),
                validateTokenService());
    }

    @Bean
    public RefreshTokenDelivery refreshTokenDelivery(){
        return new RefreshTokenDelivery(
                refreshTokenService());
    }

    /**
     * Auth/Infrastructure/app/security
     */

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(allowedOriginsUrl)
                        .allowedMethods("GET", "POST")
                        .maxAge(3600);
            }
        };
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        //CorsConfiguration corsConfiguration = new CorsConfiguration();
        //corsConfiguration.applyPermitDefaultValues();
        httpSecurity
                .cors()
                .and()
                .csrf()
                .disable()
        /*
        httpSecurity
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors()
                .configurationSource(request -> corsConfiguration);

         */
                .authorizeRequests()
                .antMatchers("/api/v1/signup/**")
                .permitAll()
                .antMatchers("/api/v1/login/**")
                .permitAll()
                .antMatchers("/api/v1/enableUser/**")
                .permitAll()
                .anyRequest()
                .authenticated();
        httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ManagerAuthenticator managerAuthenticator() throws Exception {
        return new ManagerAuthenticator(
                authenticationManagerBean());
    }
    /**
     * =============================Certificate======================
     */

    /**
     * certificate/application/modelMapper
     */

    @Bean
    StudentCertificateModelMapper studentCertificateModelMapper(){
        return new StudentCertificateModelMapper();
    }

    /**
     * certificate/application/createStudentCertificate
     */

    @Bean
    public CreateStudentCertificateAdapterRepository createStudentCertificateAdapterRepository(){
        return new CreateStudentCertificateAdapterRepository(
                studentCertificateDao,
                studentCertificateModelMapper());
    }

    @Bean
    public CreateStudentCertificateService createStudentCertificateService(){
        return new CreateStudentCertificateService(
                createStudentCertificateAdapterRepository(),
                generateCertificateService(),
                createQrValidatorService());
    }

    @Bean
    public CreateStudentCertificateDelivery createStudentCertificateDelivery(){
        return new CreateStudentCertificateDelivery(
                createStudentCertificateService(),
                studentCertificateModelMapper());
    }

    /**
     * certificate/application/createStudentCertificate
     */

    @Bean
    public GenerateCertificateAdapterPdfGenerator generateCertificateAdapterPdfGenerator(){
        return new GenerateCertificateAdapterPdfGenerator(
                iTextRenderPdfContract);
    }

    @Bean
    public GenerateCertificateService generateCertificateService(){
        return new GenerateCertificateService(
                generateCertificateAdapterPdfGenerator());
    }

    /**
     * certificate/application/createQrValidator
     */

    @Bean
    public CreateQrValidatorAdapterQrGenerator createQrValidatorAdapterQrGenerator(){
        return new CreateQrValidatorAdapterQrGenerator(
                zxingQrContract);
    }

    @Bean
    public CreateQrValidatorService createQrValidatorService(){
        return new CreateQrValidatorService(
                createQrValidatorAdapterQrGenerator());
    }

    /**
     * =============================Mail======================
     */

    /**
     * emailNotifier/application/modelMapper
     */

    /**
     * emailNotifier/application/sendValidateUserEmail
     */
    @Bean
    public SendValidateUserEmailAdapter sendValidateUserEmailAdapter(){
        return new SendValidateUserEmailAdapter(springJavaMailer);
    }

    @Bean
    public SendValidateUserEmailService sendValidateUserEmailService(){
        return new SendValidateUserEmailService(sendValidateUserEmailAdapter());
    }


    /**
     * =======================Shared======================
     */
//    @Bean
//    public MailModelMapper mailModelMapper(){
//        return new MailModelMapper();
//    }

    @Bean
    public SpringStringEncoder springStringEncoder(){
        return new SpringStringEncoder(passwordEncoder());
    }

    @Bean
    public StringEncoderAdapter stringEncoderAdapter(){
        return new StringEncoderAdapter(springStringEncoder());
    }

    @Bean
    public StringEncoderService stringEncoderService(){
        return new StringEncoderService(stringEncoderAdapter());
    }

}
