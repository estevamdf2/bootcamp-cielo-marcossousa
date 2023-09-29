# Desafio Bootcamp Cielo

## Objetivo

Repositório do candidato Marcos Sousa cujo objetivo e apresentar aos avaliadores a solução criada para os desafios solicitados.

* [Aplicação](#aplicação)
* [Desafios](#desafios)
* [Desafio 1](#desafio-1)
* [Desafio 2](#desafio-2)
* [Desafio 3](#desafio-3)
* [Desafio 4](#desafio-4)


## Aplicação

//todo inserir as variaveis que devem ser trocadas no application.properties com a fila SQS DA APLICAÇÃO. 

A aplicação conta com as principais tecnologias:
* Java 17
* Spring boot: 3.1.4
* Spring security: 3.1.4
* Amazon sdk: 1.11.106
* Maven


### Executando a aplicação

Abra a classe **ProspectApplication** e rode a aplicação. Este exemplo foi realizado na IDE IntelliJ

![Executando aplicação](docs/imagens/8-executar-a-aplicacao.PNG)

Após a execução você tera uma saída parecida a esta no console da aplicação.

```
2023-09-29T13:15:52.769-03:00  INFO 17964 --- [  restartedMain] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2023-09-29T13:15:52.770-03:00  INFO 17964 --- [  restartedMain] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 1642 ms
2023-09-29T13:15:52.802-03:00  INFO 17964 --- [  restartedMain] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2023-09-29T13:15:53.035-03:00  INFO 17964 --- [  restartedMain] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Added connection conn0: url=jdbc:h2:mem:testdb user=SA
2023-09-29T13:15:53.036-03:00  INFO 17964 --- [  restartedMain] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2023-09-29T13:15:53.043-03:00  INFO 17964 --- [  restartedMain] o.s.b.a.h2.H2ConsoleAutoConfiguration    : H2 console available at '/h2-console'. Database available at 'jdbc:h2:mem:testdb'
2023-09-29T13:15:53.176-03:00  INFO 17964 --- [  restartedMain] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]
2023-09-29T13:15:53.248-03:00  INFO 17964 --- [  restartedMain] org.hibernate.Version                    : HHH000412: Hibernate ORM core version 6.2.9.Final
2023-09-29T13:15:53.251-03:00  INFO 17964 --- [  restartedMain] org.hibernate.cfg.Environment            : HHH000406: Using bytecode reflection optimizer
2023-09-29T13:15:53.411-03:00  INFO 17964 --- [  restartedMain] o.h.b.i.BytecodeProviderInitiator        : HHH000021: Bytecode provider name : bytebuddy
2023-09-29T13:15:53.564-03:00  INFO 17964 --- [  restartedMain] o.s.o.j.p.SpringPersistenceUnitInfo      : No LoadTimeWeaver setup: ignoring JPA class transformer
2023-09-29T13:15:53.881-03:00  INFO 17964 --- [  restartedMain] o.h.b.i.BytecodeProviderInitiator        : HHH000021: Bytecode provider name : bytebuddy
2023-09-29T13:15:54.501-03:00  INFO 17964 --- [  restartedMain] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000490: Using JtaPlatform implementation: [org.hibernate.engine.transaction.jta.platform.internal.NoJtaPlatform]
2023-09-29T13:15:54.504-03:00  INFO 17964 --- [  restartedMain] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2023-09-29T13:15:55.137-03:00  WARN 17964 --- [  restartedMain] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
2023-09-29T13:15:55.258-03:00  INFO 17964 --- [  restartedMain] o.s.s.web.DefaultSecurityFilterChain     : Will secure any request with [org.springframework.security.web.session.DisableEncodeUrlFilter@2eecdad6, org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter@1bb4df3a, org.springframework.security.web.context.SecurityContextHolderFilter@1e66c753, org.springframework.security.web.header.HeaderWriterFilter@38737236, org.springframework.security.web.authentication.logout.LogoutFilter@50d92b17, com.adatech.cielo.prospect.domain.login.JwtAuthFilter@2d9d8721, org.springframework.security.web.savedrequest.RequestCacheAwareFilter@63a45434, org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter@347427a5, org.springframework.security.web.authentication.AnonymousAuthenticationFilter@bc87c9b, org.springframework.security.web.session.SessionManagementFilter@37bbdc69, org.springframework.security.web.access.ExceptionTranslationFilter@179c0c0d, org.springframework.security.web.access.intercept.AuthorizationFilter@d871d4c]
2023-09-29T13:15:56.510-03:00  INFO 17964 --- [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
2023-09-29T13:15:56.554-03:00  INFO 17964 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2023-09-29T13:15:56.563-03:00  INFO 17964 --- [  restartedMain] c.a.cielo.prospect.ProspectApplication   : Started ProspectApplication in 5.852 seconds (process running for 7.195)
```

### Usando a aplicação

Chame eu seu browser o seguinte endereço para ter acesso ao Swagger da API: [http://localhost:8080/swagger-ui/index.html#](http://localhost:8080/swagger-ui/index.html#)

Você tera acesso a está página da aplicação:

![Página inicial aplicação](docs/imagens/9-index-swagger.PNG)

### Autenticação

Para utilizar os endpoints da aplicação e necessário realizar o login na aplicação. Use um dos seguintes usuários cadastrados para esta finalidade:

| username     | password | role  |
|--------------|----------|-------|
| ayrton.senna | ehTetra  | ADMIN |
| alain.prost  | Balestri | USER  |


Neste exemplo clicaremos no botão **Try it out** para habilitar a chamada ao endpoint

![Botão try it out](docs/imagens/10-botao-try-it-out.PNG)

Agora informe os dados no formulário e faça a requisição clicando no botão **Execute**

![Botão execute](docs/imagens/11-botao-execute.PNG)

Se os dados do usuário estiverem corretos você receberá um json semelhante a este

```json 
{
  "userId": 1,
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJheXJ0b24uc2VubmEiLCJpYXQiOjE2OTYwMDQ3MDUsImV4cCI6MTY5NjAwODMwNX0.5jhyBAcNI966iHLWAjFfd07FKgGOSVKGFMVWP0-VToQ"
}
```

#### Usar o Bearer Token para autenticar as requisições

Na parte superior da página clique no campo **Authorize** para inserir o token da autenticação

![Botão Authorize](docs/imagens/12-botao-authorize.PNG)

No formulário aberto copie o token gerado no endpoint `POST /login`, insira o no campo value e clique no botão **Authorize**

![Modal Authorize](docs/imagens/13-dialog-authorize.PNG)
![Modal logado](docs/imagens/14-dialog-logado.PNG)

Agora e fechar a modal e realizar as requisições na API.

#### Realizando uma chamada

Após realizar os passos descritos no tópico [Autenticação](#autenticação). Escolha um dos endpoints e faça a chamada.

![Chamada ao endpoint](docs/imagens/15-chamada-list-pessoas-juridicas.PNG)

## Desafios

### Desafio 1 
_Modelar uma API REST com operações que possibilitem a **criação, alteração,
exclusão e consulta** de pré-cadastros de clientes. O entregável deverá ser um
documento **swagger**_.

![Modelagem de dados](docs/imagens/7-desafio1-modelagem-aplicacao.png "Modelagem de dados da aplicação")

_Modelagem de dados da aplicação_

[SWAGGER versão 1](src/main/resources/desafio1/api-docs.json)




### Desafio 2

_a) incluir na API criada no desafio “1” uma nova operação que possibilite a **retirada do
próximo cliente** da fila de atendimento e retorne os dados disponíveis_

_b) implementar na linguagem java uma estrutura de dados para uma fila utilizando
apenas tipos de dados primitivos (sem utilizar classes java.util.*), onde seja possível
acrescentar e retirar clientes na fila no modelo FIFO (First In, First Out)._

_c) contemplar as regras da história de usuário através da implementação da operação
modelada no item “a”, utilizando a estrutura de fila criada no item “b”_

_d) Implementar cobertura de 70% de testes unitários_

Os seguintes endpoints foram criados para esta finalidade;

* `GET /clientes`: retorna a lista de clientes na fila de atendimento.
* `GET /clientes/retirar`: retira o primeiro cliente na fila para atendimento.

Para testar a funcionalidade compartilhamos os seguintes dados para cadastro:

**Pessoa Física**

Cadastre os seguintes dados utilizando o endpoint `POST /clientes/pf`:

```json
{
  "mcc": "0763",
  "cpf": "84122878004",
  "nomePessoa": "Ana Moreira Froes",
  "emailPessoa": "ana.moreira@ada.tech"
}
```

**Pessoa Jurídica**

Cadastre os seguintes dados utilizando o endpoint `POST /clientes/pj`:

```json
{
  "mcc": "1711",
  "cpf": "63944134028",
  "cnpj": "88824062000169",
  "razaoSocial": "Tudo frio Ar condicionado",
  "nomeContato": "Marcelino Freitas",
  "emailContato": "marcelino.freitas@ada.tech"
}
```

```json
{
  "mcc": "5814",
  "cpf": "93885056038",
  "cnpj": "55064933000134",
  "razaoSocial": "Bolos fofos bolos confeitados",
  "nomeContato": "Gabriela Lemos",
  "emailContato": "gabi.fofos@ada.tech"
}
```

Após realizar o cadastro chame o endpoint `GET /clientes` para visualizar a lista de clientes na fila de atendimento.

![Resposta endpoint get /clientes](docs/imagens/1-get-clientes.PNG "Resposta endpoint GET /clientes")

#### Simulando uma alteração
Vamos simular uma alteração no cadastro do cliente 2, **Tudo frio Ar condicionado** para colocar este cliente em último na fila.

Vamos utilizar o endpoint `PUT /clientes/pj`
segue um json com a sugestão de alteração:
```json
{
  "mcc": "1711",
  "cpf": "63944134028",
  "cnpj": "88824062000169",
  "razaoSocial": "Tudo frio Ar condicionado - LTDA",
  "nomeContato": "Marcelino Freitas",
  "emailContato": "marcelino.freitas@ada.tech"
}
```

Após a alteração o cliente aparecerá em último na fila de atendimento.

![Resposta endpoint get /clientes após alterar cliente](docs/imagens/2-get-clientes.PNG "Resposta endpoint GET /clientes após alterar cliente")

#### Retirando um cliente para atendimento

Conforme demonstrado pelos passos anteriores. Quando for realizada a chamada ao endpoint `GET /clientes/retirar`. Iremos receber o seguinte cliente da fila:
```json
{
  "mcc": "0763",
  "cpf": "84122878004",
  "nomePessoa": "Ana Moreira Froes",
  "emailPessoa": "ana.moreira@ada.tech"
}
```

Faça a chamada ao endpoint e obtenha a seguinte resposta

![Resposta endpoint get /clientes/retirar](docs/imagens/3-get-clientes-retirar.PNG "Resposta endpoint GET /clientes/retirar")

Ao chamar novamente o endpoint `GET /clientes` teremos somente dois clientes na fila de atendimento.

![Resposta endpoint get /clientes após retirar cliente](docs/imagens/4-get-clientes-apos-retirada.PNG "Resposta endpoint GET /clientes após alterar cliente")

### Desafio 3

_Desenhe e implemente uma nova solução para a fila de atendimento, utilizando a
solução de mensageria SQS da AWS._

Implementado junto a aplicação um serviço que se comunica com a Amazon SQS cujo objetivo e gerenciar a fila de atendimento de novos clientes cadastrados no **Prospect clientes**.

Segue abaixo o desenho da arquitetura para o atendimento do desafio.

![Arquitetura Amazon SQS](docs/imagens/5-arquitetura-desafio3.png "Arquitetura Amazon SQS")

Novos endpoints foram acrescentados a aplicação para a utilização desta funcionalidade:
* `GET /clientes/aws`: Retorna a lista de clientes cadastrados no Amazon SQS.
* `GET /clientes/aws/retirar`: Retira o primeiro cliente na fila do Amazon SQS para atendimento.

### Desafio 4

_a) identifique um débito técnico de Segurança da Informação na aplicação;_

_b) detalhe o débito técnico identificado, informando a criticidade e possíveis
consequências;_

_c) planeje as atividades técnicas para o desenvolvimento da solução;_

_d) implemente a solução._


A API da aplicação atualmente encontra-se pública, ou seja, qualquer pessoa que tenha acesso a URL da solução conseguirá realizar
requisições para ela. Isto é um ponto crítico pois desta forma podemos expor dados sigilosos, sensíveis da aplicação e dos clientes.

O Spring conta com um módulo que trata do controle de acesso e autenticação à aplicação. O modulo **Spring Security** conta com os seguintes recursos:
* Suporte e extensibilidade para autenticação e autorização;
* Proteção contra ataques como sessão fixa, clickjacking, cross site request forgery, etc;
* Integração a servlet API;
* Dentre outros;

#### Planejamento das atividades

Para a implementação do módulo de segurança. Seguiremos as seguintes etapas:

* [Inclusão de dependências](#inclusão-de-dependências)
* [Classes para ativar as configurações de segurança](#classes-para-ativar-as-configurações-de-segurança)
* [Criar entidade para armazenar os dados de usuário](#criar-entidade-para-armazenar-os-dados-de-usuário)
* [Configuração para o serviço de autenticação](#configuração-para-o-serviço-de-autenticação)
* [Ligando as classes de serviço as configurações de segurança](#ligando-as-classe-de-serviço-as-configurações-de-segurança)
* [Autenticação com JWT](#autenticação-com-jwt)
* * [Crie a classe JWTService](#crie-a-classe-jwtservice)
* * [Crie a classe JWTAuthFilter](#crie-a-classe-jwtauthfilter)
* [Habilitar a autenticação via token ao Swagger](#habilitar-a-autenticação-via-token-ao-swagger-)


##### Inclusão de dependências
Desta forma temos que inserir as seguintes bibliotecas ao projeto:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-test</artifactId>
    <scope>test</scope>
</dependency>
```


A aplicação se comportará no modo _STATELESS_ onde a API não armazenará o estado da sessão. Com isso em cada requisição temos que confirmar qual usuário está
autenticado a fim de verificar se ele terá permissões para obter os recursos.
Para que não se envie os dados de login e senha em todas as requisições utilizaremos o JSON Web Token. Veremos no tópico [Autenticação com JWT](#autenticação-com-jwt)

##### Classes para ativar as configurações de segurança

##### SecurityConfig
O Spring Security 6.1 usa Lambda DSL - permite melhor legibilidade do que usando concatenação com and(). Esse recurso existe desde a versão 5.2 e é uma preparação para a versão 7.

Defina um bean para a configuração das autorizações de segurança. Veremos abaixo as principais configurações desta classe

```java
//Habilitando o controle de autorização aos métodos e classes.
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthFilter jwtAuthFilter;
@Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable()) //Desabilitando o csrf
                //Definindo a politica de sessão como STATELESS
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //Liberando o acesso ao banco H2.
                .headers(header -> header.frameOptions(frame -> frame.disable()))
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers(PathRequest.toH2Console()).permitAll()
                                // Liberando o acesso sem autenticação ao endpoint /login e as páginas da documentação com Swagger
                                .requestMatchers(new AntPathRequestMatcher("/login")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/v3/api-docs/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/swagger-ui.html")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/swagger-ui/**")).permitAll()
                                .anyRequest().authenticated()
                )
                .authenticationProvider(this.authenticationProvider)
                .addFilterBefore(this.jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
```
##### Criar entidade para armazenar os dados de usuário

Precisamos criar uma classe para representar a nossa entidade Usuario. Ela implementará a interface `org.springframework.security.core.userdetails.UserDetails`. Segue abaixo a classe:

###### Entidade
```java 
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Log4j2
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String username;
    @Email
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private boolean accountExpired;
    private boolean accountLocked;
    private boolean credentialsExpired;
    private boolean disabled;

    public Usuario(String username, String email, String password, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    /**Metodos implementados pelo UserDetails 
     * Com elas você pode definir algumas propriedades como
     * se a conta já está expirada, se a credencial expirará etc**/
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>(Collections.singletonList(new SimpleGrantedAuthority("ROLE_" +this.role.name())));
    }

    @Override
    public boolean isAccountNonExpired() {
        return !this.accountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !this.credentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return !this.disabled;
    }
}
```

###### Role
Vamos criar um enum com os perfis utilizados pela aplicação:

```java 
public enum Role {
    USER, ADMIN
}
```

###### Repositório
Criaremos um repository para e implementaremos a seguinte interface

```java
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);
}
```
###### Serviço
Criaremos uma clase de serviço que implementará a seguinte interface `org.springframework.security.core.userdetails.UserDetailsService`
```java
@Service
@RequiredArgsConstructor
public class AutenticacaoService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.usuarioRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuário nao encontrado"));
    }
}
```
##### Configuração para o serviço de autenticação

Para realizar a autenticação precisamos criar uma outra classe de configuração para definir três beans
* Password encoder
* Authentication provider
* Autentication manager

```java
@Configuration
@RequiredArgsConstructor
public class AutenticacaoConfig {

    private final AutenticacaoService autenticacaoService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(this.autenticacaoService);
        provider.setPasswordEncoder(this.passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
```

##### Ligando as classes de serviço as configurações de segurança

Voltaremos agora a classe **SecurityConfig** para incluir as configurações de segurança criadas na etapa anterior.

```java 
//Habilitando o controle de autorização aos métodos e classes.
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthFilter jwtAuthFilter;
@Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable()) //Desabilitando o csrf
                //Definindo a politica de sessão como STATELESS
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //Liberando o acesso ao banco H2.
                .headers(header -> header.frameOptions(frame -> frame.disable()))
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers(PathRequest.toH2Console()).permitAll()
                                // Liberando o acesso sem autenticação ao endpoint /login e as páginas da documentação com Swagger
                                .requestMatchers(new AntPathRequestMatcher("/login")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/v3/api-docs/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/swagger-ui.html")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/swagger-ui/**")).permitAll()
                                .anyRequest().authenticated()
                )
                // Adicionando as configurações da classe de autenticação ao Spring Security
                .authenticationProvider(this.authenticationProvider)
                .addFilterBefore(this.jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
```
#### Autenticação com JWT

O JWT é um sistema de transferência de dados que pode ser enviado via URL, POST ou em um cabeçalho HTTP(header) de maneira "segura",
essa informação é assinada digitalmente, por exemplo com o algoritmo HMAC, ou um par de chaves pública/privada usando RSA.

Para utilizá-lo no projeto vamos adicionar as seguintes dependências:

```xml
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.11.5</version>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-impl</artifactId>
    <version>0.11.5</version>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-jackson</artifactId>
    <version>0.11.5</version>
    <scope>runtime</scope>
</dependency>
```

Agora vamos gerar um token para ser utilizado pela aplicação. Caso tenha o Node instalado você pode executar o seguinte comando:

```shell 
node -e "console.log(require('crypto').randomBytes(32).toString('hex'))"
```

Uma outra alternativa é utilizar algum site que use o bcrypt para gerar um hash. No meu caso eu utilizei o site [online Bcrypt](https://www.javainuse.com/onlineBcrypt)

![Site bcrypt](docs/imagens/6-site-bcrypt.PNG "Site online bcrypt")

#### Crie a classe JWTService

Está classe de serviço vamos criar o token para o usuário autenticado, verificar a validade do token recebido vendo se
ele ainda e válido ou se já esta expirado.
```java
@Service
public class JwtService {
    
    //Pega a chave da aplicação para gerar o token do usuário
    @Value("${security.jwt.token.secret-key}")
    private String secret;

    //Cria o token para o usuário 
    public String createToken(UserDetails usuario){
        final var now = LocalDateTime.now();
        return Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(usuario.getUsername())
                .setIssuedAt(convertFromLoadDateTime(now))
                //Define o tempo de 1 hora para expiração do token
                .setExpiration(convertFromLocalDateTime(now.plusHours(1)))
                .signWith(this.getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Date convertFromLocalDateTime(LocalDateTime now) {
        return Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
    }

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(Base64.getEncoder().encode(this.secret.getBytes()));
    }

    private static Date convertFromLoadDateTime(LocalDateTime now) {
        return Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
    }

    //Obtém o nome de usuário através do token
    public String extractUsername(String token) {
        return this.extractClaims(token, Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims, T> resolver) {
        final var claims = this.extractAllClaims(token);
        return resolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(this.getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    //Verifica se o token ainda está valido
    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = this.extractUsername(token);
        return username.equals(userDetails.getUsername()) && !this.isTokenExpired(token);
    }
    //Verifica se o token foi expirado
    private boolean isTokenExpired(String token) {
        return this.extractExpiration(token).before(new Date(System.currentTimeMillis()));
    }

    private Date extractExpiration(String token) {
        return this.extractClaims(token, Claims::getExpiration);
    }
}
```

#### Crie a classe JWTAuthFilter

Vamos criar uma de serviço de filtro que herdará de `org.springframework.web.filter.OncePerRequestFilter`. O método a ser sobrescrito deverá fazer três coisas:
1. Se existe um Bearer token passado no cabeçalho da requisição, extraia o nome de usuário do token;
2. Verifique se o contexto de segurança já está definido, senão, defina;
3. Verifique se o token se é válido e adicione as informações da autenticação ao contexto de segurança.

```java 
@Component //Define a classe como uma classe de configuração.
@RequiredArgsConstructor
@Log4j2
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    
    //Inclui o token ao cabeçalho da requisição
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(header == null || !header.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }
        final String jwt = header.substring(7);
        this.authenticateUserFromToken(request, jwt);
        filterChain.doFilter(request, response);
    }

    //Valida o token recebido na autenticação e seta o usuário logado ao AuthTokenOnSecurityContext
    private void authenticateUserFromToken(HttpServletRequest request, String jwt) {
        final String username = this.jwtService.extractUsername(jwt);
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if(this.jwtService.isTokenValid(jwt, userDetails)){
                this.setAuthTokenOnSecurityContext(request,userDetails);
            }
        }
    }

    //Seta as informações do token ao SecurityContextHolder.
    private void setAuthTokenOnSecurityContext(HttpServletRequest request, UserDetails userDetails) {
        var authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }
}
```

Agora para que as configurações referentes ao JWT tenha efeito vamos adicioná-la a classe de configuração **SecurityConfig**

```java 
//Habilitando o controle de autorização aos métodos e classes.
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthFilter jwtAuthFilter;
@Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable()) //Desabilitando o csrf
                //Definindo a politica de sessão como STATELESS
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //Liberando o acesso ao banco H2.
                .headers(header -> header.frameOptions(frame -> frame.disable()))
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers(PathRequest.toH2Console()).permitAll()
                                // Liberando o acesso sem autenticação ao endpoint /login e as páginas da documentação com Swagger
                                .requestMatchers(new AntPathRequestMatcher("/login")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/v3/api-docs/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/swagger-ui.html")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/swagger-ui/**")).permitAll()
                                .anyRequest().authenticated()
                )
                // Adicionando as configurações da classe de autenticação ao Spring Security
                .authenticationProvider(this.authenticationProvider)
                // Adicionandos as configurações referentes ao JWT para uso do token
                .addFilterBefore(this.jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
```  
   
### Habilitar a autenticação via token ao Swagger

Para incluir a autenticação no Swagger e habilitar o cabeçalho para as requisições faça os seguintes passos.


1. Abra a classe **SpringDocConfigurations** é inclua os seguintes trechos de código:

Aqui você definirá o SecurityScheme para JWT
```java 
private SecurityScheme createAPIKeyScheme() {
    return new SecurityScheme().type(SecurityScheme.Type.HTTP)
        .bearerFormat("JWT")
        .scheme("bearer");
}
```

Aqui você incluirá as configurações de segurança relacionada ao bean do OpenApi
```java
.components(new Components()
        .addSecuritySchemes("bearer-key",
        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT"))) 
```

Método completo e a variável criada com o SecurityScheme para melhorar a visualização:

```java 
private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
        .bearerFormat("JWT")
        .scheme("bearer");

@Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().
                        addList("Bearer Authentication"))
                .components(new Components().addSecuritySchemes
                        ("Bearer Authentication", createAPIKeyScheme()))
                .info(new Info()
                        .title(nomeProjeto)
                        .description(descricao)
                        .contact(new Contact()
                                .name("Marcos Sousa")
                                .email("backend@adatech.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://empresa/api/licenca")));
    }
```

Agora você terá a opção de se autenticar via swagger, obter o token e realizar a chamada aos endpoints.
Link referente ao [Swagger](https://www.baeldung.com/spring-boot-swagger-jwt)