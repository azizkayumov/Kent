# Kent
Kent is a set of extension functions to build dynamic Android layouts. The purpose of this library is to make UI code clean, easy to read and as simple as possible without introducing extra logic.  

Kent consists of two parts:  
  * Layouts - a fast and simple way to write dynamic Android layouts  
  * Fragment - lightweight fragment (not reinventing the wheel here)

### Layouts ([wiki](https://github.com/AbduazizKayumov/Kent/wiki))

*Kent Layouts* is a set of helper functions for writing Android layouts. A simple UI code with Kent Layouts:
```kotlin      
verticalLayout {
    lparams(matchParent, matchParent)

    textView {
        text = "Hello, World!"
    }
}
```
There is no magic here, only two extension functions that turns noisy code to simple DSL syntax without XML.

### Fragment ([wiki](https://github.com/AbduazizKayumov/Kent/wiki))
*Kent Fragment* is an abstract class with its view and aims to solve the problems:  
1. Painless fragment management than support lib's fragment
2. Swipe back to dismiss
3. Smooth enter / exit animations
4. Theme / language changes without ****Activity restart****  

Simplicity is the key idea to create custom fragment: support lib's fragment is 3000+ lines of code whereas Kent Fragment is <150 lines. Refer to wiki for more usage.
