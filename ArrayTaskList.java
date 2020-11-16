package ua.edu.sumdu.ta.Karina.pr5;

public class ArrayTaskList extends AbstractTaskList
{
    public ArrayTaskList()
    {
        mTask = new Task[0];
        mLength = 0;
        count = 0;
        numberOfLists++;
    }

    @Override
    public void add(Task task)
    {
        try
        {
            if (task == null)
            {
                throw  new NullPointerException();
            }

            if (count >= mLength)
            {
                resize();
            }

            task.title = startOfTitle + " " + task.title;
            mTask[count] = task;
            count++;
        }
        catch (NullPointerException e)
        {
            System.out.println("Method add: The task does not exist (task = null)");
        }
    }

    @Override
    public void add(int index, Task task)
    {

    }

    private void resize()
    {
        Task[] data = new Task[mLength + value];

        System.arraycopy(mTask, 0, data, 0, mLength);//mLength always >=0

        mTask = null;
        mTask = data;
        mLength = mLength + value;
    }

    @Override
    public void remove(Task task)
    {
        try
        {
            if (task == null)
            {
                throw  new NullPointerException();
            }

            int index = -1;

            for (int i = 0; i  < mLength; i++)
            {
                if(mTask[i].equals(task))
                {
                    index = i;
                    break;
                }
            }

            if (index >= 0)
            {
                Task[] data = new Task[mLength - 1];

                for (int i = 0; i < index; i++)
                {
                    data[i] = mTask[i];
                }


                for (int i = index + 1; i < mLength; i++)
                {
                    data[i-1] = mTask[i];
                }

                mTask = null;
                mTask = data;
                mLength--;
                count--;
            }
        }
        catch (NullPointerException e)
        {
            System.out.println("Method remove: The task does not exist (task =  null)");
        }
    }

    @Override
    public Task getTask(int index) throws NullPointerException
    {
        try
        {
            if(index < 0 || index >= count)
            {
                throw new IndexOutOfBoundsException();
            }
                return mTask[index];
        }
        catch (IndexOutOfBoundsException e)
        {
            System.out.println("Method getTask: Invalid index");
        }

        return null;
    }

    @Override
    public Task[] incoming(int from, int to) throws NullPointerException
    {
        try
        {
            if (to <= from || to < 0 || from < 0)
            {
                throw new IllegalArgumentException();
            }

            Task[] data = new Task[mLength];

            int k = 0;

            for (int i = 0; i < count; i++)
            {
                if(mTask[i].isActive())
                {
                    if(mTask[i].isRepeated())
                    {
                        for(int j = mTask[i].start; j <= mTask[i].end; j += mTask[i].repeat)
                        {
                            if(j > from && j <= to)
                            {
                                data[k] = mTask[i];
                                k++;
                                break;
                            }
                        }
                    }
                    else
                    {
                        if (mTask[i].time > from && mTask[i].time <= to)
                        {
                            data[k] = mTask[i];
                            k++;
                        }
                    }
                }
            }

            Task[] incoming = new Task[k];
            if(k > 0)
            {
                System.arraycopy(data, 0, incoming, 0, k);
            }

            return incoming;
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Method incoming: Invalid from and to");
        }

        return null;
    }

}
