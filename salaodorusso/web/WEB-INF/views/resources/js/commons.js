var StatusRetorno =
        {
            OPERACAO_OK: 570, // a operação ocorreu com sucesso e sem quaisquer problemas
            FALHA_VALIDACAO: 610, // os dados enviados estão inconsistentes ou algum campo faltou
            NAO_AUTORIZADO: 900, // o servidor não autorizou o acesso a algum recurso
            FALHA_INTERNA: 800, // ocorreu um problema no lado servidor ao processar a operação
            NAO_ENCONTRADO: 405   // o recurso/dados em questão não existem mais
        };


function showMsg(title, text)
{
    alert(text);
}

function getViewBase(viewName)
{
    var result = '';
    $.ajax({
        url: ('/salaodorusso/getviewbase?viewName=' + viewName),
        async: false,
        success: function (viewBase)
        {
            result = viewBase;
        }
    });
    return result;
}