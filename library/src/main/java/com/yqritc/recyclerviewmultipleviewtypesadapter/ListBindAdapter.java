package com.yqritc.recyclerviewmultipleviewtypesadapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Adapter class for managing data binders when the order of binder is in sequence
 *
 * Created by yqritc on 2015/03/19.
 */
public class ListBindAdapter extends DataBindAdapter {

    private List<DataBinder> mBinderList = new ArrayList<>();

    @Override
    public int getItemCount() {
        int itemCount = 0;
        for (DataBinder binder : mBinderList) {
            itemCount += binder.getItemCount();
        }
        return itemCount;
    }

    @Override
    public int getItemViewType(int position) {
        int itemCount = 0;
        for (int viewType = 0; viewType < mBinderList.size(); viewType++) {
            itemCount += mBinderList.get(viewType).getItemCount();
            if (position < itemCount) {
                return viewType;
            }
        }
        throw new IllegalArgumentException("arg position is invalid");
    }

    @Override
    public <T extends DataBinder> T getDataBinder(int viewType) {
        return (T) mBinderList.get(viewType);
    }

    @Override
    public int getPosition(DataBinder binder, int binderPosition) {
        int viewType = mBinderList.indexOf(binder);
        if (viewType < 0) {
            throw new IllegalStateException("binder does not exist in adapter");
        }

        int position = binderPosition;
        for (DataBinder dataBinder : mBinderList) {
            position += dataBinder.getItemCount();
        }
        return position;
    }

    @Override
    public int getBinderPosition(int position) {
        int binderItemCount;
        for (int i = 0; i < mBinderList.size(); i++) {
            binderItemCount = mBinderList.get(i).getItemCount();
            if (position - binderItemCount < 0) {
                break;
            }
            position -= binderItemCount;
        }
        return position;
    }

    public List<DataBinder> getBinderList() {
        return mBinderList;
    }

    public void add(DataBinder binder) {
        mBinderList.add(binder);
    }

    public void addAll(List<DataBinder> dataSet) {
        mBinderList.addAll(dataSet);
    }

    public void addAll(DataBinder... dataSet) {
        mBinderList.addAll(Arrays.asList(dataSet));
    }

    public void set(int index, DataBinder binder) {
        mBinderList.set(index, binder);
    }

    public void remove(int index) {
        mBinderList.remove(index);
    }

    public void remove(DataBinder binder) {
        mBinderList.remove(binder);
    }

    public void clear() {
        mBinderList.clear();
    }
}