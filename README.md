[![build](https://github.com/AbduazizKayumov/Kent/workflows/build/badge.svg)](https://github.com/AbduazizKayumov/Kent/actions) [![Download](https://api.bintray.com/packages/abduazizkayumov/Kent/layouts/images/download.svg?version=1.0.1)](https://bintray.com/abduazizkayumov/Kent/layouts/1.0.1/link) [![License](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)
# Kent
Kent is a set of extension functions to build dynamic Android layouts. The purpose of this library is to make UI code clean, easy to read and as simple as possible without introducing extra logic. Simple UI code with Kent Layouts:
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
2. You can't insert code logic inside XML
3. XML is not null-safe which leads to NPEs.  
4. You repeat `android` and `app` almost in every line
5. XML is static, you can't dynamically add / remove views according to app logic, for example:
`if isLoggedIn: addOwnerPhoto() else addLoginButton()`
and many more [reasons](https://github.com/Kotlin/anko/wiki/Anko-Layouts#why-a-dsl)

The main goal is to supersede [Anko Layouts](https://github.com/Kotlin/anko/wiki/Anko-Layouts#why-anko-layouts) (since it is [deprecated](https://github.com/Kotlin/anko/blob/master/GOODBYE.md)) with support for AndroidX and excluding all other gigantic util code.  
#### Install Kent Layouts:
Add these dependencies to your app level *build.gradle*:
```
implementation "androidx.core:core-ktx:1.3.2"

implementation "androidx.appcompat:appcompat:1.2.0"

implementation "androidx.recyclerview:recyclerview:1.1.0"

implementation "com.google.android.material:material:1.2.1"

implementation "androidx.constraintlayout:constraintlayout:2.0.4"

implementation 'com.kent.layouts:layouts:1.0.1'
```
#### How to use it?
You can build your UI dynamically in a conventional way, but even in Kotlin, it would be longer:
```
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val parent = LinearLayout(this)
    parent.orientation = LinearLayout.VERTICAL
    parent.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
    val btn = Button(this)
    btn.layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
    btn.text = "Click me!"
    btn.setOnClickListener {
        Toast.makeText(this, "Hello, World!", Toast.LENGTH_SHORT).show()
    }
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
          button {
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
<details>
  <summary>ViewGroups</summary>  

  ```
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
  ```
</details>
<details>
  <summary>Views</summary>  

  ```
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
    * ViewPager & ViewPager2
  ```
</details>

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

#### How to use with custom views?
Create extension function of ViewGroup for your custom view, example for `SwipeRefreshLayout`:

    inline fun ViewGroup.swipeRefresh(init: SwipeRefreshLayout.() -> Unit = {}): SwipeRefreshLayout {
        val a = SwipeRefreshLayout(context).apply(init)
        addView(a)
        return a
    }
#### More examples:
See more examples in [samples](https://github.com/AbduazizKayumov/Kent/tree/master/samples/src/main/java/com/kent/sample)  
An open source app entirely written using Kent Layouts: [Flashcards Maker](https://github.com/AbduazizKayumov/Flashcard-Maker-Android)
