#### __Имеется функция `f`, определённая ниже__

```Java 8
static boolean f(String args, boolean flag) {
    System.out.print(args + " ");
    return flag;
}
```

#### __Что выведет на консоль следующий код?__
```Java 8
if (f("1", true) || f("2", true) && f("3", false)) {
    System.out.print("Complete");
}
```

#### a. 1 2 3 Complete
#### b. 1 Complete
#### c.	1 2 3
#### d.	3 Complete
#### e.	Ошибка компиляции