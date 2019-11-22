
<%@ include file='header.jsp'%>


<div id="global">
	<div id="categorie">
		<ul>
			<c:forEach var="categorie" items="${categories}">
				<li class="select"><a
					href="categorie?idCategorie=${categorie.idCategorie}">${categorie.nom }</a></li>
			</c:forEach>
		</ul>
	</div>



	<article>
		<c:forEach var="recette" items="${Recettes}">
			<header>
				<img class="imgRecette" src="img/${recette.photo}" width="300px"
					height="242px" alt="Tartiflette" />
				<p><c:forEach var="i" begin="1" end="${recette.moyenneNote}">
					<span class="fa fa-star checked"></span>
				</c:forEach>
				<c:forEach var="i" begin="${recette.moyenneNote+1}" end="5">
					<span class="fa fa-star "></span>
				</c:forEach></p>
				<a href="recette?id=${recette.idRecette}">
					<h1 class="titreRecette">${recette.titre}</h1>
				</a>
				<time> ${recette.dateCreation} </time>
			</header>
			<p>${recette.description}</p>
		</c:forEach>
	</article>
	<hr />
</div>
<footer id="piedBlog"> Blog réalisé par </footer>


</body>
</html>