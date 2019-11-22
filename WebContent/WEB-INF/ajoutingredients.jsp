<%@ include file='header.jsp'%>
<div id="global">


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

		<hr/>
			<header>
			<h2 id="titreIngredient">Ingrédients</h2>
				<ul>
					<c:forEach var="ingredient" items="${Ingredients}">
						<li>${ingredient.quantite}${ingredient.unit} de 
							${ingredient.nomIngredient}</li>

					</c:forEach>
				</ul>
				</header>
				<hr/>
				<!-- Ajouter le formulaire d'ajout d'ingredients -->
				<form method="post" action="ajoutIngredient?idRecette=${sessionScope.recette.idRecette}">
					<input id="nomIngredient" name="nomIngredient" type="text" class="inputChamp"
						placeholder="Le nom de votre Ingredient" /> 
					<input id="quantite" name="quantite" type="text" class="inputChamp"
						placeholder="La quantite de cette ingredient" /> 
						
						<select id ="unit" name="unit">

						<option>Tasse </option>
						<option selected="yes">Cuillere</option>
						<option>Cuillere a soupe</option>
						<option>Cafe</option>
						<option>Cuillere a soupe</option>
						<option>Pince</option>
						<option>L</option>
						<option>cL</option>
						<option>mL</option>
						<option>kG</option>
						<option>g</option>
						<option>dg</option>
						<option>cg</option>
						<option>mg</option>
						<option>Unite</option>

						</select>
						
						<br>
						<br>
				
				
				
				
				<input type="submit" value="J'ajoute cet ingredient" class="submitBtn" />
				<br></br>
				<a class="primaryBtn login" href="recettesEdit?idMembre=${sessionScope.membre.idMembre}">
				Ajouter a mes recettes</a>
				</form>
				
</div>
<footer id="piedBlog"> Blog réalisé par </footer>


</body>
</html>