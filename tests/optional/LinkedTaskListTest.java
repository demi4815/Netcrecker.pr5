package ua.edu.sumdu.ta.Karina.pr5.tests.optional;

import org.junit.Before;
import ua.edu.sumdu.ta.Karina.pr5.*;

public class LinkedTaskListTest extends TaskListTest {

    @Before
    public void before()
    {
        tasks = new LinkedTaskList();
    }
}
