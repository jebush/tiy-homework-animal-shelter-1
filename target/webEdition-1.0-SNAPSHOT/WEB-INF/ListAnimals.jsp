<%@ page import="java.util.ArrayList" %>
<%@ page import="com.andrewRnagel.animalshelter.entity.Animal" %>
<%@ page import="com.andrewRnagel.animalshelter.entity.Type" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Animal Shelter</title>
        <link rel="icon" href="/images/doghouse512px.png">
        <link rel="stylesheet" href="/css/AnimalShelter_ListAnimals.css">

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
            <div class="AnimalSearch">
                <h2>List Animals</h2>
                <p>Search for an animal:</p>
                    <form action="" method="get">
                        <input type="text" placeholder="Name" id="name" name="name" value="${name}">
                        <select id="type" name="type">
                            <option value="0" selected disabled>Type</option>
                            <%--Populate the dropdown menu with Types from DB--%>
                            <% for (Type type : (ArrayList<Type>) request.getAttribute("typesList")) { %>
                                <option value="<%= type.getTypeID() %>" <%= request.getAttribute("type") != null && type.getTypeID() == (int)request.getAttribute("type") ? "selected='true'" : "" %>  >
                                    <%= type.getType() %>
                                </option>
                            <% } %>
                        </select>
                    <%--TODO: set max based on high DB number, lock to array of ints? --%>
                        <input type="number" placeholder="ID" id="animalID" name="animalID" min="1" value="${animalID}">
                        <button>Search</button>
                    </form>
            </div>

            <% for (Animal animal : (ArrayList<Animal>) request.getAttribute("animalsList")) { %>
            <article class="AnimalCard">
                <div class="AnimalPicture">
                    <img src="<%= animal.getPicture() %>" alt=""/>
                </div>
                <div class="AnimalStats">
                    <ul>
                        <h1><a href="/EditAnimal?animalID=<%= animal.getAnimalID()%>" class="AnimalName"><%= animal.getName() %></a></h1>
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