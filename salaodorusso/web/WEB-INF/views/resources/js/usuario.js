/* global StatusRetorno */

var usuarioAtual = null;
$(document).ready(function ()
{
    var usuario = getUsuario();
    if (usuario === null)
        window.location.href = '/salaodorusso/dicas';

    carregarDadosUsuario(usuario);
});

function carregarDadosUsuario(usuario)
{
    $('#txNomeUsuario').val(usuario.nome);
    $('#txEmailUsuario').val(usuario.email);
    $('#txSenhaUsuario').val(usuario.senha);
    $('#txTelefoneUsuario').val(usuario.telefone);
    $('#txCelularUsuario').val(usuario.celular);
    $('#txLogradouroUsuario').val(usuario.endereco.logradouro);
    $('#txNumeroUsuario').val(usuario.endereco.numero);
    $('#txCepUsuario').val(usuario.endereco.cep);
    $('#txComplementoUsuario').val(usuario.endereco.complemento);
    $('#txMunicipioUsuario').val(usuario.endereco.municipio);
    $('#txBairroUsuario').val(usuario.endereco.bairro);
}

function getUsuario()
{
    var usuario = null;
    if ($.cookie('UID') === null)
    {
        $.removeCookie('UID');
        return usuario;
    }

    $.ajax({
        url: '/salaodorusso/usuario-findbykey',
        data: {dataKey: $.cookie('UID')},
        async: false,
        type: 'POST',
        success: function (response)
        {
            if (response.status === StatusRetorno.OPERACAO_OK)
                usuario = response.entity;

            if (response.status === StatusRetorno.NAO_AUTORIZADO)
            {
                showMsg('Login expirado', response.msg);
                $.removeCookie('UID');
            }
        }
    });

    usuarioAtual = usuario;
    return usuario;
}

function salvarInfoUsuario(salvarEndereco)
{
    var usuario = {
        id: usuarioAtual.id,
        nome: $('#txNomeUsuario').val(),
        email: $('#txEmailUsuario').val(),
        senha: $('#txSenhaUsuario').val(),
        telefone: $('#txTelefoneUsuario').val(),
        celular: $('#txCelularUsuario').val(),
        endereco_id: usuarioAtual.endereco.id
    };

    if (salvaUsuario(usuario, salvarEndereco))
        showMsg('Sucesso', 'Suas informações foram atualizadas!');
}

function salvaUsuario(usuario, salvarEndereco)
{
    var salvo = false;
    var url = '/salaodorusso/usuario-save';

    $.ajax({
        url: url,
        type: 'POST',
        async: false,
        data: usuario,
        success: function (response)
        {
            if (response.status === StatusRetorno.OPERACAO_OK)
            {
                if (salvarEndereco)
                    salvaEndereco(usuario);
                salvo = true;
            }
            if (response.status === StatusRetorno.FALHA_INTERNA)
                showMsg('ERRO', response.msg);
            if (response.status === StatusRetorno.FALHA_VALIDACAO)
                showMsg('Atenção', response.msg);
        }
    });
    return salvo;
}

function salvaEndereco(usuario)
{
    var endereco = {
        id: usuarioAtual.endereco.id,
        complemento: $('#txComplementoUsuario').val(),
        bairro: $('#txBairroUsuario').val(),
        cep: $('#txCepUsuario').val(),
        municipio: $('#txMunicipioUsuario').val(),
        logradouro: $('#txLogradouroUsuario').val(),
        numero: $('#txNumeroUsuario').val()
    };

    var url = '/salaodorusso/endereco-save';
    $.post(url, endereco, function (response)
    {
        if (response.status === StatusRetorno.OPERACAO_OK)
        {
            if (usuario.endereco_id === 0)
            {
                usuario.endereco_id = response.entity;
                usuarioAtual.endereco.id = response.entity;
                salvaUsuario(usuario, false);
            }
        }

        if (response.status === StatusRetorno.FALHA_INTERNA)
            showMsg('ERRO', response.msg);
        if (response.status === StatusRetorno.FALHA_VALIDACAO)
            showMsg('Atenção', response.msg);
    });
}

function efetuarLogout()
{
    var dataKey = $.cookie('UID');
    var url = '/salaodorusso/usuario-logout';
    $.post(url, {dataKey: dataKey}, function (response)
    {
        if (response.status === StatusRetorno.OPERACAO_OK)
        {
            $.removeCookie('UID');
            window.location.href = '/salaodorusso/dicas';
        }
    });
}