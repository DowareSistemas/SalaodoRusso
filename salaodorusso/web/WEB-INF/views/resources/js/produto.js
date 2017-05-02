/* global StatusRetorno */

function listarProdutosPaginaAdmin()
{
    var viewBase = getViewBase('item-tabela-produtos-administrador');
    var url = '/salaodorusso/produto-search';
    $.post(url, {searchTerm: ''}, function (response)
    {
        $('#tabela-produtos-admin').html('');
        var produtos = response.entity;
        $.each(produtos, function ()
        {
            var produto = this;
            $('#tabela-produtos-admin').append(viewBase
                    .replace(/{id}/g, produto.id)
                    .replace('{descricao}', produto.descricao)
                    .replace('{valor}', produto.valor.toFixed(2)));
        });
    });
}

function salvarProduto()
{
    var produto = {
        id: $('#txIdProduto').val(),
        descricao: $('#txDescricaoProduto').val(),
        nome: $('#txNomeProduto').val(),
        valor: $('#txValorProduto').val()
    };
    var url = '/salaodorusso/produto-save';
    $.post(url, produto, function (response)
    {
        if (response.status === StatusRetorno.OPERACAO_OK)
        {
            uploadImgProduto(response.entity);
            limparCamposCadastroProduto();
            listarProdutosPaginaAdmin();
        }
    });
}

function limparCamposCadastroProduto()
{
    $('#inputFotoProduto').val(null);
    $('#txIdProduto').val('0');
    $('#txDescricaoProduto').val('');
    $('#txNomeProduto').val('');
    $('#txValorProduto').val('0');
}

function uploadImgProduto(produto_id)
{
    if ($('#inputFotoProduto').val() === '' || $('#inputFotoProduto').val() === null)
        return;

    var url = '/salaodorusso/produto-setimage?produto_id=' + produto_id;
    $('#formUploadImgProduto').attr('action', url);
    $('#formUploadImgProduto').ajaxForm({
        async: false,
        success: function (response)
        {
            if (response.status === StatusRetorno.FALHA_INTERNA)
                showMsg('Erro', response.msg);
        }
    }).submit();
}

function carregarProdutoEdicao(produto_id)
{
    var url = '/salaodorusso/produto-find';
    $.post(url, {id: produto_id}, function (response)
    {
        if (response.status === StatusRetorno.NAO_ENCONTRADO)
            showMsg('Dados desatualizados', response.msg);
        
        if(response.status === StatusRetorno.OPERACAO_OK)
        {
            var produto = response.entity;
            $('#txIdProduto').val(produto.id);
            $('#txNomeProduto').val(produto.nome);
            $('#txDescricaoProduto').val(produto.descricao);
            $('#txValorProduto').val(produto.valor.toFixed(2));
        }
    });
}

function excluirProduto(produto_id)
{
    var url = '/salaodorusso/produto-remove';
    $.post(url, {id: produto_id}, function (response)
    {
        if (response.status === StatusRetorno.FALHA_INTERNA)
            showMsg('ERRO', response);

        if (response.status === StatusRetorno.OPERACAO_OK)
            listarProdutosPaginaAdmin();
    });
}