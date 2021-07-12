package com.OrangeTalents.zupMercadoLivre.fechamentoCompra;

public interface RetornoGatewayPagamento {

	Transacao toTransacao(Compra compra);
}
