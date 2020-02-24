[ ![Download](https://api.bintray.com/packages/abduazizkayumov/Kent/layouts/images/download.svg?version=1.0.0) ](https://bintray.com/abduazizkayumov/Kent/layouts/1.0.0/link) [![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)
# Kent
Kent is a set of extension functions to build dynamic Android layouts. The purpose of this library is to make UI code clean, easy to read and as simple as possible without introducing extra logic.  

Kent consists of two parts:  
  * Layouts - a fast and simple way to write dynamic Android layouts  
  * Fragment - lightweight fragment (not reinventing the wheel here)

## Layouts

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
#### Why dynamic layouts?
Usually, XML is used to build Android UI. Major disadvantages of XML:
1. XML is parsed on the device wasting battery and CPU time, and view inflations affects the [cold start time](https://developer.android.com/topic/performance/vitals/launch-time#cold)
2. You can't insert code logic inside XML, for example:
`if profile.isOwner: addOwnerPhoto()`
3. XML is not null-safe which leads to NPEs.

The purpose of this project is to supersede [Anko Layouts](https://github.com/Kotlin/anko/wiki/Anko-Layouts#why-anko-layouts) with support for AndroidX.  
#### How to use it?
You can build your UI dynamically in a conventional way, but even in Kotlin, it would be longer:
```
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val parent = LinearLayout(this)
    parent.orientation = LinearLayout.VERTICAL
    parent.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)

    val text = TextView(this)
    text.layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
    text.text = "Hello, World!"

    val btn = Button(this)
    btn.layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
    btn.text = "Click me!"
    btn.setOnClickListener {
        Toast.makeText(this, "Hello, World!", Toast.LENGTH_SHORT).show()
    }

    parent.addView(text)
    parent.addView(btn)
    setContentView(parent)
}
```
While with the DSL syntax of Kent Layouts, the same UI code of the same activity:  
```
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    verticalLayout {
          lparams(matchParent, matchParent)
          textView {
              layoutParams = LinearLayout.LayoutParams(wrapContent, wrapContent)
              text = "Hello, World!"
          }
          button {
              layoutParams = LinearLayout.LayoutParams(wrapContent, wrapContent)
              text = "Click me!"
              setOnClickListener {
                  toast("Hello, World!")
              }
          }
    }
}
```
#### How about using it in `fragments` or for `RecyclerView ViewHolders`?

All you need is `Context`, this will simply create `TextView` inside `FrameLayout` using `context`:
```
return ViewHolder(parent.context.frameLayout {
    layoutParams = ViewGroup.LayoutParams(matchParent, dip(56))
    padding = dip(16)
    setRippleEffect()

    textView {
        layoutParams = FrameLayout.LayoutParams(wrapContent, wrapContent)
        text = "Hello, Kent!"
    }
})
```
Most of the standard Android views and view groups have their extensions so that you don't need to waste time writing extensions, here is the complete list:
  * ViewGroups
    * AppBarLayout
    * BottomAppBar
    * CollapsingToolbarLayout
    * ConstraintLayout
    * CoordinatorLayout
    * DrawerLayout
    * FrameLayout
    * GridLayout
    * LinearLayout
    * RelativeLayout
  * Views
    * Buttons
      * Button
      * MaterialButton
      * FloatingActionButton
    * CardView & MaterialCardView
    * CheckBox & MaterialCheckBox
    * Chip and ChipGroup
    * DatePicker
    * ImageView
    * NavigationView & BottomNavigationView
    * Progress: CircularProgress and LinearProgress
    * RadioButton, MaterialRadioButton, RadioGroup
    * RatingBar
    * RecyclerView
    * ScrollView & NestedScrollView
    * SeekBar
    * Switch & MaterialSwitch
    * Tabs: TabItem & TabLayout
    * TextView
    * EditText & TextInputEditText & TextInputLayout
    * View
    * ViewGroup & ViewGroup2

#### How it works?
Using Kotlin extension functions, we can convert this conventional code:

    val btn = Button(this)
    btn.layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
    btn.text = "Click me!"
    parent.addView(btn)
into DSL syntax as an extension function of `ViewGroup`:

    inline fun ViewGroup.button(init: AppCompatButton.() -> Unit = {}): AppCompatButton {
        val a = AppCompatButton(context).apply(init)
        addView(a)
        return a
    }
And the result is more beatiful, concise and easy to understand code:

    button {
        layoutParams = LinearLayout.LayoutParams(wrapContent, wrapContent)
        text = "Click me!"
    }

#### How to use Custom Views?
Create extension function of ViewGroup for your custom view, example for `SwipeRefreshLayout`:

    inline fun ViewGroup.button(init: SwipeRefreshLayout.() -> Unit = {}): SwipeRefreshLayout {
        val a = SwipeRefreshLayout(context).apply(init)
        addView(a)
        return a
    }

#### Install Kent Layouts:
Add this dependency to your app level *build.gradle*:

```
implementation 'com.kent.layouts:layouts:1.0.0'
```
Make sure you have jcenter() in your project level *build.gradle*.

#### More examples:
See more examples in [samples](https://github.com/AbduazizKayumov/Kent/tree/master/samples/src/main/java/com/kent/sample)

## Fragment ([wiki](https://github.com/AbduazizKayumov/Kent/wiki))
*Kent Fragment* is an abstract class with its view and aims to solve the problems:  
1. Painless fragment management than support lib's fragment
2. Swipe back to dismiss
3. Smooth enter / exit animations
4. Theme / language changes without ****Activity restart****  

Custom fragment was created to remove Google as my "single point of failure" and to make things simpler. Simplicity is the key idea to create custom fragment: support lib's fragment is 3000+ lines of code whereas Kent Fragment is <150 lines. Refer to wiki for more usage.
