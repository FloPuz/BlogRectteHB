
<%@ include file = 'header.jsp' %>

	<div id="global">
		<div id="categorie">
		 <ul>
			
					
					
				
			
               		 <li class="select"><a href="ajoutRecette?idMembre=${membre.idMembre}">Ajouter une recette</a> 
              				
                	</li>
            	
			</ul> 	
		
		</div>
		
		<article>
		<c:forEach var="recette" items="${recettesByMembre}"> 
			<header>
			<a class="primaryBtn login" href="login">Modifier</a>
			
				<img class="imgRecette" src="img/${recette.photo}"
					width="300px" height="242px" alt="Tartiflette" /> 
					<!--  Photo de la recette -->
			<a class="primaryBtn login" href="deleteRecette?idRecette=${recette.idRecette}">Supprimer</a>		
					<a href="recette?id=${recette.idRecette}"><!-- Ici je vais devoir changer 
																le href poitant sur mon jsp permettant la
																modification d'une recette propre -->
					<!--  id de la recette -->
					
					<h1 class="titreRecette">${recette.titre} </h1>
					<!-- Titre de la recette -->
					
				</a>
				<time>${recette.dateCreation} </time>
					<!-- Date création de la recette -->
					
			</header>
			<p>${recette.description }</p>
				<!-- Description de la recette 
					Faire une boucle pour afficher toutes les recettes de la categories
				-->
				<hr/>
				</c:forEach>
				
		</article>
		

		
	</div>

	<footer id="piedBlog"> Blog réalisé par </footer>

</body>
</html>