
<%@ include file = 'header.jsp' %>

	<div id="global">
		<div id="categorie">
			<ul>
			<!-- Id et nom de la cat�gorie
				 Ajouter class="selected" sur le li de la cat�gorie s�lectionn�e 
					Faire une boucle pour afficher toutes les cat�gories. 
					
					
					<li class="selected"><a href="categorie?idCategorie=">Entr�e</a></li>					
					<li><a href="categorie?idCategorie=">Plat principal</a></li> -->
					
					
				
				<c:forEach var="category" items="${categories}">
               		 <li class="select"><a href="categorie?idCategorie=${category.getIdCategorie()}">${category.getNom()}</a> 
              				
                	</li>
            	</c:forEach>
			</ul>
		</div>
		
		<article>
		<c:forEach var="recette" items="${recettesCategorie1}"> 
			<header>
				<img class="imgRecette" src="img/${recette.photo}"
					width="300px" height="242px" alt="Tartiflette" /> 
					<!--  Photo de la recette -->
					
					<a href="recette?id=${recette.idRecette}">
					<!--  id de la recette -->
					
					<h1 class="titreRecette">${recette.titre} </h1>
					<!-- Titre de la recette -->
					
				</a>
				<time>${recette.dateCreation} </time>
					<!-- Date cr�ation de la recette -->
					
			</header>
			<p>${recette.description }</p>
				<!-- Description de la recette 
					Faire une boucle pour afficher toutes les recettes de la categories
				-->
				</c:forEach>
		</article>
		
		<hr />
		
	</div>

	<footer id="piedBlog"> Blog r�alis� par </footer>

</body>
</html>