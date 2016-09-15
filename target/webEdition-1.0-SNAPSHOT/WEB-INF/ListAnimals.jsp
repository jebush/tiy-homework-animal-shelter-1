<%@ page import="java.util.ArrayList" %>
<%@ page import="com.andrewRnagel.animalshelter.entity.Animal" %>
<%@ page import="com.andrewRnagel.animalshelter.entity.Type" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Animal Shelter</title>
        <link rel="stylesheet" href="css/AnimalShelter_ListAnimals.css">
        <link rel="icon" href="/images/doghouse512px.png">
        <header>
            <div class="HeaderText">
                 <h1>Animal Shelter</h1>
            </div>
        </header>
        <nav>
            <ul>
                <li><a href="/">List Animals</a></li>
                <li><a href="EditAnimals.jsp">Add an Animal</a></li>
            </ul>
        </nav>
    </head>
    <body>
        <section>
            <div class="AnimalSearch">
                <h2>List Animals</h2>
                <p>Search for an animal:</p>
                    <form action="" method="post">
                        <input type="text" placeholder="Name" id="name" name="name">
                        <select name="type">
                            <option value="" disabled selected>Type</option>
                            <%--Populate the dropdown menu with Types from DB--%>
                            <% for (Type type : (ArrayList<Type>) request.getAttribute("typesList")) { %>
                            <option value ="<%= type.getTypeID() %>">
                                <%= type.getType() %>
                            </option>
                            <% } %>
                        </select>
                        <input type="number" placeholder="ID" id="id" name="id" min="1">
                        <button>Search</button>
                    </form>
            </div>

            <% for (Animal animal : (ArrayList<Animal>) request.getAttribute("animalsList")) { %>
            <article class="AnimalCard">
                <div class="AnimalPicture">
                    <img src="/images/X.jpg" alt="Blank"/>
                </div>
                <div class="AnimalStats">
                    <ul>
                        <h1><a href="EditAnimals.jsp" class="AnimalName"><%= animal.getName() %></a></h1>
                        <li><span class="LabelText">Type: </span><%= animal.getType() %></li>
                        <li><span class="LabelText">Breed: </span><%= animal.getBreed() %></li>
                        <li><span class="LabelText">Description: </span><%= animal.getDescription() %></li>
                        <li><span class="LabelText">Notes: </span><a href="AnimalNotes.jsp"><%=animal.getAnimalNotes().size() %> notes</a> </li>
                    </ul>
                </div>
            </article>
            <% } %>
        </section>
    </body>
</html>