/* global StatusRetorno */

function listarDicasPaginaAdmin()
{
    var viewBase = getViewBase('item-tabela-dicas-administrador');
    var url = '/salaodorusso/dica-search';
    var params = {
        searchTerm: '',
        paginaAtual: 0,
        numeroRegistros: 3000
    };

    $.post(url, params, function (result)
    {
        $('#tabela-dicas-administrador').html('');
        var dicas = result.entity;
        $.each(dicas, function ()
        {
            var dica = this;
            $('#tabela-dicas-administrador').append(viewBase
                    .replace(/{id}/g, dica.id)
                    .replace('{titulo}', dica.titulo));
        });
    });
}

function salvarDica()
{
    var dica = {
        id: $('#txIdDica').val(),
        titulo: $('#txTituloDica').val(),
        descricao: $('#txDescricaoDica').val()
    };
    var url = '/salaodorusso/dica-save';
    $.post(url, dica, function (result)
    {
        if (result.status === StatusRetorno.FALHA_VALIDACAO)
            showMsg('Atenção', result.msg);

        if (result.status === StatusRetorno.FALHA_INTERNA)
            showMsg('Erro', result.msg);

        if (result.status === StatusRetorno.OPERACAO_OK)
        {
            uploadImgDica(result.entity);
            listarDicasPaginaAdmin();
            limparCamposCadastroDica();
        }
    });
}

function limparCamposCadastroDica()
{
    $('#txIdDica').val('0');
    $('#txTituloDica').val('');
    $('#txDescricaoDica').val('');
    $('#inputFotoDica').val(null);
}

function uploadImgDica(dica_id)
{
    if ($('#inputFotoDica').val() === '')
        return;

    var url = '/salaodorusso/dica-setimage?dica_id=' + dica_id;
    $('#formUploadImgDica').attr('action', url);
    $('#formUploadImgDica').ajaxForm({
        async: false,
        success: function (response)
        {
            if (response.status === StatusRetorno.FALHA_INTERNA)
                showMsg('Erro', response.msg);
        }
    }).submit();
}

function carregarDicaEdicao(dica_id)
{
    var url = '/salaodorusso/dica-find';
    $.post(url, {id: dica_id}, function (result)
    {
        var dica = result.entity;
        $('#txIdDica').val(dica.id);
        $('#txTituloDica').val(dica.titulo);
        $('#txDescricaoDica').val(dica.descricao);
    });
}

function excluirDica(dica_id)
{
    var url = '/salaodorusso/dica-remove';
    $.post(url, {id: dica_id}, function (result)
    {
        if (result.status === StatusRetorno.OPERACAO_OK)
            listarDicasPaginaAdmin();

        if (result.status === StatusRetorno.FALHA_INTERNA)
            showMsg('ERRO', result.msg);
    });
}

function listarDicasPaginaListagem()
{
    var viewBase = getViewBase('item-listagem-dicas');
    var url = '/salaodorusso/dica-search';
    var params = {
        searchTerm: '',
        paginaAtual: 0,
        numeroRegistros: 50
    };

    $.post(url, params, function (result)
    {
        $('#container-dicas').html('');
        var dicas = result.entity;
        $.each(dicas, function ()
        {
            var dica = this;
            $('#container-dicas').append(viewBase
                    .replace(/{id}/g, dica.id)
                    .replace('{titulo}', dica.titulo)
                    .replace('{descricao}', dica.descricao));

            $('#fotoDica' + dica.id).attr('src',
                    '/salaodorusso/dica-getimage?image_name=' + dica.foto);
        });
    });
}

function listarDicasIndex()
{
    var viewBaseItem = getViewBase('item-caroucel-dicas');
    var viewBaseIndicador = getViewBase('indicador-caroucel-dicas');

    var url = '/salaodorusso/dica-top3';
    $.post(url, function (result)
    {
        var dicas = result.entity;
        for (var i = 0; i < dicas.length; i++)
        {
            var dica = dicas[i];

            $('#indicadores-caroucel-dicas').append(viewBaseIndicador
                    .replace(/{id}/g, dica.id)
                    .replace('{indice}', i));

            $('#caroucel-dicas').append(viewBaseItem
                    .replace(/{id}/g, dica.id));

            if (dica.foto !== '')
                $('#imgDicaCaroucel' + dica.id).attr('src',
                        '/salaodorusso/dica-getimage?image_name=' + dica.foto);

            if (i === 0)
            {
                $('#itemCarrocelDicas' + dica.id).attr('class',
                        'item active');

                $('#indicadorCaroucelDica' + dica.id).attr('class',
                        'active');
            }
        }
    });
}