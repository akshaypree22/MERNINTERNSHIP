var a = [1,"two","three"]
console.log(a)

var b = [{
    "name" : "jp",
    "class" : "CSE A"
},
{
    "name" : "aji",
    "class" : "CSE A"
},
{
    "name" : "akshay",
    "class" : "CSE A"
}]
b.forEach((person) => {
    console.log(person.name,person.class)
})