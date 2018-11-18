use arguileBar
INSERT into vaso(`marca`,`tamanho`,`cor`) values ('marca1', '30', 'branco')

INSERT into essencia(`quantidade`) values ('10')
insert into carvao(`marca`,`preco`,`quantidade`) values ('marca1','20','10');
select * from carvao
select * from essencia where marca like '%morango%'

insert into rosh(`marca`, `cor`, `material`, `tamanho`, `preco`, `quantidade`) values ('Marca1', 'Azul', '20', 'pequeno', '50.00', '2');

insert into vaso(`marca`, `cor`, `altura`, `largura`, `preco`) values ('Marca1', 'Transparente', '15', '30.47', '50.00');
insert into bebida(`nome`, `marca`, `peso`, `preco`, `quantidade`) values ('Coca-Cola', 'coca-cola', '0.35', '3.50', '30');
insert into statuspedido(`status`) values('preparando');
insert into statuspedido(`status`) values('pronto');
insert into funcionario(`nome_func`, `cpf_func`, `data_adm`, `cargo`, `salario`, `endereco`, `telefoneCelular`, `telefoneResidencial`, `sobrenome`) values ('Bruno', '08391201929', '2017-05-01', 'Garçom', '1400', 'Rua Leopoldia 573' , '41995214547', '4137254124', 'Felipe Pinto');


select * from funcionario
select * from bebida

 
delete from essencia where marca='' or sabor='' or preco='' or categoria=''
select * from aluminio
UPDATE essencia set essencia.marca='new', essencia.quantidade='10' where id_essencia=11
Select * from mesa
select * from carvao
select * from statuspedido
select * from essencia
select * from bebida
select * from aluminio
insert into pedido (`mesa_id_mesa`,`status_id_status`,`funcionario_id_funcionario`) values ('1', '1', '1')
update mesa set mesa.nome='mesa 1' where id_mesa=1
insert into pedido_essencia(`pedido_id_pedido`,`essencia_id_essencia`) values ('1','5')
insert into pedido_essencia(`pedido_id_pedido`,`essencia_id_essencia`) values ('1','11')
insert into pedido_bebida(`pedido_id_pedido`,`bebida_id_bebida`) values ('1','1')
select * from pedido_essencia
select * from statuspedido

select m.nome, e.sabor, f.nome_func, s.descricao from mesa m, essencia e, funcionario f, statuspedido s, pedido p, pedido_essencia pe where p.mesa_id_mesa=m.id_mesa
and pe.pedido_id_pedido=p.mesa_id_mesa and pe.essencia_id_essencia=e.id_essencia and f.id_funcionario=p.funcionario_id_funcionario and s.id_status=p.status_id_status
 

select e.sabor from essencia e, pedido p, pedido_essencia pe where p.id_pedido


select m.nome, e.sabor, f.nome_func, s.descricao, b.nome from mesa m, essencia e, funcionario f, statuspedido s, pedido p, pedido_essencia pe, bebida b, pedido_bebida pb where p.mesa_id_mesa=m.id_mesa
and pe.pedido_id_pedido=p.mesa_id_mesa and pe.essencia_id_essencia=e.id_essencia and f.id_funcionario=p.funcionario_id_funcionario and s.id_status=p.status_id_status and p.mesa_id_mesa=pb.pedido_id_pedido and b.id_prodDiversos=pb.bebida_id_bebida

ALTER TABLE `arguilebar`.`pedido_bebida` 
ADD CONSTRAINT `constraint_pedido`
	FOREIGN KEY (`pedido_id_pedido`)
    REFERENCES `ArguileBar`.`pedido` (`mesa_id_mesa`);




