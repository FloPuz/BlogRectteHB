<%@ include file = 'header.jsp' %>

	<div id="global">
		<h1>Inscription</h1>
		<div id="inscription">
			<form method="post" action="inscription">
				<input id="nom" name="nom" type="text" class="inputChamp"
					placeholder="Votre nom *" value = '<c:out value = "${member.nom}" />' /><br /> <input id="pseudo"
					name="pseudo" type="text" class="inputChamp"
					placeholder="Votre pseudo *" value = '<c:out value = "${member.pseudo}" />' /><br > <input id="email"
					name="email" type="text" class="inputChamp"
					placeholder="Votre email *" value = '<c:out value = "${member.email}" />'/><br > <input id="mdp" name="mdp"
					type="password" class="inputChamp"
					placeholder="Votre mot de passe *" /><br />
					<input id="mdpconf" name="mdpconf"
					type="password" class="inputChamp"
					placeholder="Mot de passe identique *" /><br /> <br /> <input
					type="submit" value="Je 'm'inscris" class="submitBtn" />
			</form>
		</div>
		<div id="erreur">
			<p>
			 ${success}
			 ${member.nom}</p>
		</div>
		<div id="erreur">
			<p>${info}</p>
		</div>
	</div>

	<footer id="piedBlog"> Blog réalisé par </footer>
</body>
</html>