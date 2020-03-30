## Before the session

- Study the slides and the committed code
- Delete the all of the files in `java` and `resources`

## During the Session

- Explain that this one is going to be a doozy
- Think of it like a cooking show if you've ever watched them: it's going to take a while but we're going to explain things as we go to cement understanding
- we're going to give you two slightly different recipes to achieve the same result
- Take it a slide at a time, implementing as you go. Discuss the why behind what you're doing as you're doing - some supporting slides are supplied
- Be sure to show them the Generate feature of Intellij when creating the bean

## Note about Sequences

- Hibernate gets super nerdy about how primary keys can get generated
- By default, we're going to rely on PostgresSQL to do our sequencing for us
- To do so we must always define a Generator and a Value - the styles are different between the XML mapping and the annotation, but they mean the same thing