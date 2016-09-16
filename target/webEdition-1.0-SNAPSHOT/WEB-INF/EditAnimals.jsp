<%@ page import="java.util.ArrayList" %>
<%@ page import="com.andrewRnagel.animalshelter.entity.Animal" %>
<%@ page import="com.andrewRnagel.animalshelter.entity.Type" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Animal Shelter</title>
        <link rel="stylesheet" href="css/AnimalShelter_EditAnimals.css">
        <link rel="icon" href="/images/doghouse512px.png">

        <header>
            <div class="HeaderText">
            <h1>Animal Shelter</h1>
            </div>
        </header>
        <nav>
            <ul>
                <li><a href="/">List Animals</a></li>
                <li><a href="/EditAnimal">Add an Animal</a></li>
            </ul>
        </nav>
    </head>
    <body>
        <section>
            <div class="EditAnimal">
                <h2>Add/Edit An Animal</h2>
                <p>Note: (*) indicates a required field!</p><br>
                    <div>
                        <form class="AnimalDetails" action="/EditAnimal" method="post">
                            <input type="hidden" id="animalID" name="animalID" value="${animal.getAnimalID()}" />
                            <div class="LabelText">
                                <label for="name">Name*: </label>
                            </div>
                            <div>
                                <input type="text" placeholder="Name" id="name" name="name" value="${animal.getName()}"><br>
                            </div>
                            <div class="LabelText">
                                <label for="type">Type*: </label>
                            </div>
                            <div>
                                <select id="type" name="type">
                                    <option value="" disabled>Type</option>
                                    <%--Populate the dropdown menu with Types from DB--%>
                                    <% for (Type type : (ArrayList<Type>) request.getAttribute("typesList")) { %>
                                        <option value="<%= type.getTypeID() %>" <%= type.getType().equals(((Animal)request.getAttribute("animal")).getType()) ? "selected='true'" : "" %>>
                                    <%= type.getType() %>
                                    </option>
                                    <% } %>
                                </select><br>
                            </div>
                            <div class="LabelText">
                                <label for="breed">Breed: </label>
                            </div>
                            <div>
                                <input type="text" placeholder="Breed(Opt.)" id="breed" name="breed" value="${animal.getBreed()}"><br>
                            </div>
                            <div class="LabelText">
                                <label for="description">Description*: </label>
                            </div>
                            <div>
                                <textarea id="description" name="description" placeholder="Description" maxlength="64">${animal.getDescription()}</textarea><br>
                            </div>
                            <div class="LabelText">
                                <label for="photo">Photo: </label>
                            </div>
                            <div>
                                <input type="file" class=fileupload id="photo" name="photo"><br>
                            </div>
                            <div class="LabelText">
                                <label for="save"></label>
                            </div>
                            <div class="SaveButton">
                                <button id="save">Save Animal</button>
                            </div>
                        </form>
                    </div>
            </div>
        </section>
    </body>
</html>