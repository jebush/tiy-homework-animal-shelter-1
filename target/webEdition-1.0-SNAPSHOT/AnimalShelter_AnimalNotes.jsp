<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Animal Shelter</title>
        <link rel="stylesheet" href="css/AnimalShelter_AnimalNotes.css">
        <link rel="icon" href="/images/doghouse512px.png">
        <header>
            <div class="HeaderText">
                 <h1>Animal Shelter</h1>
            </div>
        </header>
        <nav>
            <ul>
                <li><a href="AnimalShelter_ListAnimals.jsp">List Animals</a></li>
                <li><a href="AnimalShelter_EditAnimals.jsp">Add an Animal</a></li>
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
                    <img src="/images/CatSilhouette.png" alt="Cat"/>
                </div>
                <div class="AnimalStats">
                    <ul>
                        <h1><a href="AnimalShelter_EditAnimals.jsp" class="AnimalName">Bob</a></h1>
                        <li><span class="LabelText">Type: </span>Cat</li>
                        <li><span class="LabelText">Breed: </span>Tabby</li>
                        <li><span class="LabelText">Description: </span>Big fat and fuzzy</li>
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
                        <tr>
                            <td>08/29/16</th>
                            <td>Went to visit vet.</td>
                            <td><button class="DeleteButton"></button></td>
                        </tr>
                        <tr>
                            <td>08/30/16</th>
                            <td>Only eats filet mignon.</td>
                            <td><button class="DeleteButton"></button></td>
                        </tr>
                    </table>
                </div>
            </article>
            <article>
                <div class="AddAnimalNote">
                    Add a note:
                    <p>
                        <textarea class="AddAnimalNoteText">This cat seriously needs a bath. This means we need to buy some chainmail.</textarea>
                    </p>
                </div>
                <div>
                    <button class="AddNoteButton">Add Note</button>
                </div>
            </article>
        </section>
    </body>
</html>