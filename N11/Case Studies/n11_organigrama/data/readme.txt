The type Collection has a very simple contract - it implies basically only that you have a bunch of
elements, you can add and remove elements, check whether the collections contains a specific
element, and iterate over them. If that's all your code needs, use Collection -  it gives the code
that uses your API the greatest freedom.

The type List has in its contract that the elements have an order that will not change except
through explicit manipulation, that you can get() the n'th element, that add() puts new elements  at
the end, etc. If your code depends on any of these properties, you should use List.


ArrayList is just a concrete implementation of List and should never be used in an API.
Its usage should only ever be an implementation detail  (unless you have some very specific
requirements).