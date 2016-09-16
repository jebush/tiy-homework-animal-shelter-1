<%@ page import="java.util.ArrayList" %>
<%@ page import="com.andrewRnagel.animalshelter.entity.Note" %>
<%@ page import="com.andrewRnagel.animalshelter.entity.Animal" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Animal Shelter</title>
        <link rel="stylesheet" href="/css/AnimalShelter_AnimalNotes.css">
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
            <div class="AnimalNotes">
                <h2>Animal Notes</h2>
            </div>
            <article class="AnimalCard">
                <div class="AnimalPicture">
                    <img src="${animal.getPicture()}" alt=""/>
                </div>
                <div class="AnimalStats">
                    <ul>
                        <h1><a href="/EditAnimal?animalID=${animal.getAnimalID()}" class="AnimalName">${animal.getName()}</a></h1>
                        <li><span class="LabelText">Type: </span>${animal.getType()}</li>
                        <li><span class="LabelText">Breed: </span>${animal.getBreed()}</li>
                        <li><span class="LabelText">Description: </span>${animal.getDescription()}</li>
                    </ul>
                </div>
            </article>
            <article>
                <div>
                    <table class="AnimalNotesTable">
                        <tr>
                            <th>Date</th>
                            <th>Note</th>
                            <th> </th>
                        </tr>
                        <% Animal animal = (Animal)request.getAttribute("animal"); %>
                        <% for (Note note : animal.getAnimalNotes()) { %>
                        <tr>
                            <td><%= note.getNoteCreationDateAsString() %></td>
                            <td><%= note.getNoteContent() %></td>
                            <td><button class="DeleteButton" id="delete" name="delete"><a href="/DeleteNote?animalID=<%= animal.getAnimalID() %>&noteID=<%= note.getNoteID() %>"></a></button></td>
                        </tr>
                       <% } %>
                    </table>
                </div>
            </article>
            <article>
                <form class="AddNote" action="/AnimalNote" method="post">
                    <input type="hidden" id="animalID" name="animalID" value="${animal.getAnimalID()}" />
                    <div class="AddAnimalNote">
                        Add a note:
                        <p>
                            <textarea class="AddAnimalNoteText" placeholder="Add A Note" id="animalNoteText" name="animalNoteText"></textarea>
                        </p>
                    </div>
                    <div>
                        <button class="AddNoteButton">Add Note</button>
                    </div>
                </form>
            </article>
        </section>
    </body>
</html>