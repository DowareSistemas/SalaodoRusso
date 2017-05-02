$(document).ready(function ()
{
    listarDicasPaginaAdmin();
    listarProdutosPaginaAdmin();

    $('#btnSalvarDica').click(function ()
    {
        salvarDica();
    });
    
    $('#btnSalvarProduto').click(function ()
    {
        salvarProduto();
    });
});