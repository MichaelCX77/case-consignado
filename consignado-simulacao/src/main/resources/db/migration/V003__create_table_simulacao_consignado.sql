create table simulacao (
    simulacao_id bigint generated by default as identity primary key,
    simulacao_codigo varchar(20) unique,
    simulacao_data datetime not null,
    simulacao_cliente_cpf varchar(14) not null,
    taxa_convenio_tipo varchar(10) not null,
    taxa_convenio_porcentagem varchar(10) not null,
    simulacao_valor_solicitado decimal(9, 2) not null,
    simulacao_valor_total decimal(15, 2) not null,
    simulacao_valor_parcela decimal(9, 2) not null,
    simulacao_quantidade_parcela integer not null,
    foreign key (taxa_convenio_tipo) references taxa_convenio (taxa_convenio_tipo)
)