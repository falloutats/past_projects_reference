# Game Documentation

## Board Class

The `Board` class has undergone several updates:

- **New Methods & Field**: The Board now includes several new methods and a new field, most of which are intuitive.
- **removeElement**: This method should remove the passed-in element from the board (from both the Cell and the elementPlace HashMap). It returns `true` if the element was removed and `false` otherwise.
- **getRow & getColumn**: These methods return the row and column coordinates of a given element. If the element is not on the board, they throw an `IllegalArgumentException`.
- **beenHugged & setHugged**: Accessor methods for the `hugged` instance variable. Note: Only `Jarvis` should call `setHugged`.

You might need to modify `placeElement` and `move` as Cells can now contain multiple `Boardable` objects.

## Boardable Interface

A new method, `share`, has been introduced in the `Boardable` interface. All concrete implementations must define how `Boardable` objects interact when sharing a `Cell`.

## Cell Class

For the `Cell` class's `toString` method:
- Return `"#"` if the Cell is not visible.
- Return `" "` if the Cell is visible but empty.
- Return the `toString` of the last element added if it's visible and contains elements. 

A Cell becomes visible if any visible element is added to it. Once visible, it remains so.

## Mobile Class

`Mobile` is a new abstract class implementing both `Boardable` and `Runnable`. All its methods are currently abstract.

## Player Class

The `Player` class is the protagonist of the game. The `move` method interfaces with the user and calls `delay`. It should call `delay` before accepting any user input. Key commands for movement include:


For `"s"`, the player indicates they wish to stay put. The `move` method also prints the board after each user input. The `run` method repeatedly calls `move` until Jarvis is hugged. The `delay` method ensures the player cannot act for `delayTime` milliseconds. The last action of `delay` is setting `delayTime` to zero. If the player is delayed, any moves entered are ignored. The `setDelay` method sets `delayTime`. A trap calls this method. If the Player is the first element in a Cell, its `share` method should always return `false`. The `toString` method returns `"*"`.

## Jarvis Class

`Jarvis` represents Professor Jarvis, the true hero. If not hugged, Jarvis randomly moves in some direction every 500 milliseconds. Every 6th move, Jarvis can lay a trap in a nearby Cell. If Jarvis shares a Cell with a Player, he receives a hug, sets the Board's `hugged` variable, and prints a relevant message. Jarvis remains invisible with its `toString` returning `"?"`.

## HomeworkTrap Class

`HomeworkTrap` is the only type of trap Jarvis sets for now. If it shares a location with a Player, the trap sets the player's delay time to 5000 milliseconds, removes itself (the trap is sprung), and prints a relevant message. Traps are invisible with their `toString` returning `" "`.

## Game Class

Not in the UML, but you'll need a `Game` class. This class contains the main method, initializing the Board, Jarvis, and Player objects. Jarvis and Player must run in separate threads. Your game should never crash. As Jarvis and Player run in different threads, some methods might need synchronization.

