list_member(X,[X|_]).
list_member(X,[_|TAIL]) :- list_member(X,TAIL).

ingredient(eggs).
ingredient(spices).
ingredient(milk).
ingredient(water).
ingredient(tealeaf).
ingredient(sugar).
ingredient(butter).
ingredient(oil).
ingredient(bread).
ingredient(mayonnaise).
ingredient(onion).
ingredient(meat).
ingredient(cheese).
ingredient(ketchup).
ingredient(salad).
ingredient(potato).
ingredient(carrot).
ingredient(beet).

dish(friedeggs).
dish(omelette).
dish(hugmug).
dish(tea).
dish(hamburger).
dish(chops).
dish(borshch).
dish(friedpotatoes).

ingredients_of([eggs, oil], friedeggs).
ingredients_of([eggs, milk], omelette).
ingredients_of([eggs, milk, spices], hugmug).
ingredients_of([water, tealeaf, sugar], tea).
ingredients_of([meat, oil, eggs, bread], chops).
ingredients_of([mayonnaise, bread, onion, meat, cheese, ketchup, salad], hamburger).
ingredients_of([onion, potato, water, carrot, beet, meat, spices], borshch).
ingredients_of([potato], friedpotatoes).

ingredient_of([A|TAIL], X) :- ingredient_of(A, X), ingredient_of([TAIL], X).
ingredient_of([A], X) :-  ingredient_of(A, X).
ingredient_of(A, X) :- ingredients_of(B, X), list_member(A, B).

