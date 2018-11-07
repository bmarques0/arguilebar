function showDiv(){
	document.getElementById("conteudo").style.visibility = "visible";
}
function obrigatorioCadastro(){
	if($("#marcaEssencia").val()== null || $("#preçoEssencia").val()== null ||
	  $("#saborEssencia").val()== null || $("#marcaEssencia").val()== null)
		break;
}
function obrigatorioPesquisa(){

}


/* <script type="text/javascript">
$(document).ready(function(){
  $("#pesquisar").onclick(function() {
    $("#conteudo").css("visibility")="visible"); 
  };
});
</script> */

/* This is a multiline comment that
    can span as many lines as necessary.  */