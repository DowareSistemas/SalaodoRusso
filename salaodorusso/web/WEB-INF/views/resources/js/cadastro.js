/* global StatusRetorno */

function efatuarLogin()
{
    var params = {
        email: $('#txEmailLogin').val(),
        senha: $('#txSenhaLogin').val()
    };

    var url = '/salaodorusso/usuario-efetualogin';
    $.post(url, params, function (response)
    {
        if (response.status === StatusRetorno.OPERACAO_OK)
        {
            window.location.href = '/salaodorusso/usuario';
            $.cookie('UID', response.entity);
        }
        
        if (response.status === StatusRetorno.NAO_ENCONTRADO)
            showMsg('Atenção', response.msg);
    });
}

function criarConta()
{
    if ($('#txSenhaUsuarioCadastro').val() !==
            $('#txConfirmarSenhaUsuarioCadastro').val())
    {
        showMsg('Atenção', 'As senhas digitadas não coincidem');
        return;
    }

    var usuario = {
        id: 0,
        nome: $('#txNomeUsuarioCadastro').val(),
        email: $('#txEmailUsuarioCadastro').val(),
        senha: $('#txSenhaUsuarioCadastro').val(),
        telefone: '',
        celular: ''
    };

    var url = '/salaodorusso/usuario-save';
    $.post(url, usuario, function (response)
    {
        if (response.status === StatusRetorno.FALHA_VALIDACAO)
            showMsg('Atenção', response.msg);

        if (response.status === StatusRetorno.FALHA_INTERNA)
            showMsg('ERRO', response.msg);

        if (response.status === StatusRetorno.OPERACAO_OK)
        {
            $.cookie('UID', response.entity);
            window.location.href = '/salaodorusso/usuario';
        }
    });
}