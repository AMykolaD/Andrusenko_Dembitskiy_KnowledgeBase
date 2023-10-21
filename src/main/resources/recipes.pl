list_member(X,[X|_]).
list_member(X,[_|TAIL]) :- list_member(X,TAIL).

ingredient(eggs).
ingredient(spices).
ingredient(milk).
ingredient(water).
ingredient(tealeaf).
dish(friedeggs).
dish(omelette).
dish(hugmug).
dish(tea).

ingredients_of([eggs], friedeggs).
ingredients_of([eggs, milk], omelette).
ingredients_of([eggs, milk, spices], hugmug).
ingredients_of([water, tealeaf], tea).

ingredient_of([A|TAIL], X) :- ingredient_of(A, X), ingredient_of([TAIL], X).
ingredient_of([A], X) :-  ingredient_of(A, X).
ingredient_of(A, X) :- ingredients_of(B, X), list_member(A, B).

