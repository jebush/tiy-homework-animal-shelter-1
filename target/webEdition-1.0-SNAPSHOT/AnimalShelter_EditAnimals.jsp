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
                <li><a href="AnimalShelter_ListAnimals.jsp">List Animals</a></li>
                <li><a href="AnimalShelter_EditAnimals.jsp">Add an Animal</a></li>
            </ul>
        </nav>
    </head>
    <body>
        <section>
            <div class="EditAnimal">
                <h2>Add/Edit An Animal</h2>
                <p>Note: (*) indicates a required field!</p><br>
                    <div>
                        <form class="AnimalDetails" action="AnimalShelter_ListAnimals.jsp" method="post">
                            <div class="LabelText">
                                <label for="name">Name*: </label>
                            </div>
                            <div>
                                <input type="text" placeholder="Name" id="name" name="name" value="Bob"><br>
                            </div>
                            <div class="LabelText">
                                <label for="species">Species*: </label>
                            </div>
                            <div>
                                <select name="species" id="species">
                                    <option value="" disabled>Type</option>
                                    <option value="" selected>Cat</option>
                                    <option value="">Dog</option>
                                </select><br>
                            </div>
                            <div class="LabelText">
                                <label for="breed">Breed: </label>
                            </div>
                            <div>
                                <input type="text" placeholder="" id="breed" name="breed"><br>
                            </div>
                            <div class="LabelText">
                                <label for="desc">Description*: </label>
                            </div>
                            <div>
                                <textarea id="desc" placeholder="Description" maxlength=64>Big fat and fuzzy</textarea><br>
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