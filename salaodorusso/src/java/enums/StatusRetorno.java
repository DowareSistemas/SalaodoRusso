/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enums;

/**
 *
 * @author Marcos Vinícius
 */
public enum StatusRetorno
{
    OPERACAO_OK(CodigosRetorno.OPERACAO_OK),
    FALHA_VALIDACAO(CodigosRetorno.FALHA_VALIDACAO),
    NAO_AUTORIZADO(CodigosRetorno.NAO_AUTORIZADO),
    FALHA_INTERNA(CodigosRetorno.FALHA_INTERNA),
    NAO_ENCONTRADO(CodigosRetorno.NAO_ENCONTRADO);

    public int valor;

    private StatusRetorno(int valor)
    {
        this.valor = valor;
    }

}
