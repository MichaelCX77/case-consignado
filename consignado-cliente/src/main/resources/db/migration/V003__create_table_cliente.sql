create table cliente (
    cliente_id  bigint generated by default as identity primary key,
    cliente_cpf varchar(14) unique not null,
    cliente_nome varchar(255) not null,
    cliente_correntista char(1) CHECK (cliente_correntista IN ('S', 'N')),
    segmento_tipo varchar(15),
    convenio_tipo varchar(15) not null,
    foreign key (segmento_tipo) references segmento (segmento_tipo),
    foreign key (convenio_tipo) references convenio (convenio_tipo)
)