<%@ include file='header.jsp'%>
<div id="global">
	<h1>Nouvelle Recette</h1>
	<c:choose>
		<c:when test="${empty recette.idRecette}">
		<article>
	
				<form method="post" action="ajoutRecette">
					<input id="titre" name="titre" type="text" class="inputChamp"
						placeholder="Le nom de votre Recette" /> <br>
						<br>
				
					<label for="photo">Sélectionnez la photo pour la recette</label>
					<input type="file" id="photo" name="photo"
						accept=".jpg, .jpeg, .png">
					<br/>
					<br>
					<textarea id="description" name="description" rows="4" cols="100"
					placeholder="La description de votre recette" class="inputTextArea"></textarea>
					<br/>
					<br>
					<label for="categorie">
					<span>Categorie</span>
					<select name="categorie" class="select-field">
						<c:forEach var = "categorie" items = "${allCategories}" >
							<option value = '${categorie.idCategorie}'> ${categorie.nom} </option></c:forEach>
				
					</select>
					<br/>
					<br>
			
						<div id="erreur">
							<p>${info}</p>
						</div>
					<input type="submit" value="J'ajoute des ingredients maintenant" class="submitBtn" />
				</form>

			</article>
		</c:when>
		
	
	<c:otherwise>
		<article>

		<header>
			<img class="imgRecette" src="img/${recette.photo}" alt="Tartiflette" />
			<!-- Insérer la photo de la recette -->

			<h1 class="titreRecette">${recette.titre}</h1>
			<!-- Insérer le titre de la recette -->
			
			<br>
			<time> ${recette.dateCreation} </time>
		</header>
		<p>${recette.description}</p>
		<!-- Insérer la description de la recette -->
		<p> Recette de : ${recette.membre.pseudo}</p>
	</article>

		
	<a class="primaryBtn login" href="ajoutIngredient?idRecette=${sessionScope.recette.idRecette}">Ajouter mes ingredients</a> 

				</form>
				
		</c:otherwise>
	</c:choose>
</div>
<footer id="piedBlog"> Blog réalisé par </footer>


</body>
</html>