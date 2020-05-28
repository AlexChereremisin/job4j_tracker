package ru.job4j.tracker;

/**
 * Item модель данных описывающая заявки в системе трекера.
 */
public final class Item {
    /**
     * ID заявки, присваивается в трекере.
     */
    private String id;
    /**
     * Название/имя заявки.
     */
    private String name;

    /**
     * Конструктор принимающий в качестве
     * параметра название/имя заявки.
     * @param newName название/имя заявки
     */
    public Item(final String newName) {
        this.name = newName;
    }

    /**
     * Геттер ID.
     * @return ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Сеттер ID.
     * @param newId ID.
     */
    public void setId(final String newId) {
        this.id = newId;
    }

    /**
     * Геттер name.
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * Сеттер name.
     * @param newName newName.
     */
    public void setName(final String newName) {
        this.name = newName;
    }

    /**
     * Переопределение метода Object.toString().
     * @return строка вида "Name : name ; id : id\r\n"
     */
    @Override
    public String toString() {
        return String.format(
                "Name : %s ; id : %s%s",
                this.name,
                this.id,
                System.lineSeparator()
        );
    }
}
