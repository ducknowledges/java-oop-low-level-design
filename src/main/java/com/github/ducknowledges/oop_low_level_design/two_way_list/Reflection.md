# Reflection ParentList<T>

## Эталонное решение

```java
abstract class ParentList<T>

  // конструктор
  public ParentList<T> ParentList();

  // команды
  public void head(); 
  public void tail(); 
  public void right(); 
  public void put_right(T value); 
  public void put_left(T value); 
  public void remove();
  public void clear(); 
  public void add_tail(T value); 
  public void remove_all(T value);
  public void replace(T value); 
  public void find(T value); 

  // запросы
  public T get();
  public bool is_head();
  public bool is_tail();
  public bool is_value();
  public int size();

  // запросы статусов
  public int get_head_status();
  public int get_tail_status();
  public int get_right_status();
  public int get_put_right_status();
  public int get_put_left_status();
  public int get_remove_status();
  public int get_replace_status();
  public int get_find_status();
  public int get_get_status();
```

```java
abstract class LinkedList<T> : ParentList<T>

  // конструктор
  public LinkedList<T> LinkedList();
```

```java
abstract class TwoWayList<T> : ParentList<T>

  // конструктор
  public TwoWayList<T> TwoWayList();

  // предусловие: левее курсора есть элемент; 
  // постусловие: курсор сдвинут на один узел влево
  public void left();

  public int get_left_status(); // успешно; левее нету элемента
```

## Рефлексия:

По сравнению с предыдущим заданием изменений не много, пред/пост условия для TwoWayList аналогичны,
единственное добавил для `left()` в предусловии, что список не пуст.

Организация такого подхода в проектировании после реализации стала понятнее,
особенно в плане выстраивания иерархии. Очевидны преимущества такой организации
которая соответствует принципу OCP. Продолжаю подтверждать на практике, что реализация
класса на основе предварительного проектирования АТД, позволяет упрощать реализацию.