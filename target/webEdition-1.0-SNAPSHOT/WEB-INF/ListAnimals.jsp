<%@ page import="java.util.ArrayList" %>
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
                <li><a href="ListAnimals.jsp">List Animals</a></li>
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

                            <% for (String type : (ArrayList<String>) request.getAttribute("typesList")) { %>
                            <option value ="">
                                <%= type %>
                            </option>
                            <% } %>

                        </select>
                        <input type="number" placeholder="ID" id="id" name="id">
                        <button>Search</button>
                    </form>
            </div>
            <article class="AnimalCard">
                <div class="AnimalPicture">
                    <img src="/images/CatSilhouette.png" alt="Cat"/>
                </div>
                <div class="AnimalStats">
                    <ul>
                        <h1><a href="EditAnimals.jsp" class="AnimalName">Bob</a></h1>
                        <li><span class="LabelText">Type: </span>Cat</li>
                        <li><span class="LabelText">Breed: </span>Tabby</li>
                        <li><span class="LabelText">Description: </span>Big fat and fuzzy</li>
                        <li><span class="LabelText">Notes: </span><a href="AnimalNotes.jsp">5 notes</a> </li>
                    </ul>
                </div>
            </article>
            <article class="AnimalCard">
                <div class="AnimalPicture">
                    <img src="/images/DogSilhouette.jpg" alt="Dog"/>
                </div>
                <div class="AnimalStats">
                    <ul>
                        <h1><a href="" class="AnimalName">Wanda</a></h1>
                        <li><span class="LabelText">Type: </span>Dog</li>
                        <li><span class="LabelText">Breed: </span>Bulldog</li>
                        <li><span class="LabelText">Description: </span>A handsome beast</li>
                        <li><span class="LabelText">Notes: </span><a href="AnimalNotes.jsp">3 notes</a> </li>
                    </ul>
                </div>
            </article>
            <article class="AnimalCard">
                <div class="AnimalPicture">
                    <img src="/images/X.jpg" alt="Blank"/>
                </div>
                <div class="AnimalStats">
                    <ul>
                        <h1><a href="" class="AnimalName">Peter Parker</a></h1>
                        <li><span class="LabelText">Type: </span>Spider</li>
                        <li><span class="LabelText">Breed: </span>Tarantula</li>
                        <li><span class="LabelText">Description: </span>Seriously, who gets a pet spider?!</li>
                        <li><span class="LabelText">Notes: </span><a href="AnimalNotes.jsp">0 notes</a> </li>
                    </ul>
                </div>
            </article>
            <article class="AnimalCard">
                <div class="AnimalPicture">
                    <img src="/images/X.jpg" alt="Blank"/>
                </div>
                <div class="AnimalStats">
                    <ul>
                        <h1><a href="" class="AnimalName">Thor</a></h1>
                        <li><span class="LabelText">Type: </span>Fish</li>
                        <li><span class="LabelText">Breed: </span>Goldfish</li>
                        <li><span class="LabelText">Description: </span>It's orange.</li>
                        <li><span class="LabelText">Notes: </span><a href="AnimalNotes.jsp">1 note</a> </li>
                    </ul>
                </div>
            </article>
        </section>
    </body>
</html>