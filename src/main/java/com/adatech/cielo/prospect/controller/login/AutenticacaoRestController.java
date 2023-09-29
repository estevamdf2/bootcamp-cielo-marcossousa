package com.adatech.cielo.prospect.controller.login;

import com.adatech.cielo.prospect.domain.login.AutenticacaoRequest;
import com.adatech.cielo.prospect.domain.login.AutenticacaoResponse;
import com.adatech.cielo.prospect.domain.usuario.UsuarioRepository;
import com.adatech.cielo.prospect.domain.login.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AutenticacaoRestController {

    private final UsuarioRepository usuarioRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping
    public AutenticacaoResponse login(@RequestBody AutenticacaoRequest autenticacao){
        Authentication authentication = new UsernamePasswordAuthenticationToken(autenticacao.username(), autenticacao.password());
        this.authenticationManager.authenticate(authentication);
        var usuario = this.usuarioRepository.findByUsername(autenticacao.username()).orElseThrow();
        String token = this.jwtService.createToken(usuario);
        return new AutenticacaoResponse(usuario.getId(), token);
    }

}
