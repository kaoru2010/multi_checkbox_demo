package com.example.kaoru.multicheckboxdemo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by kaoru on 2015/06/27.
 */
public class MyModel implements Model {
    private static final int MAX_CHECKED_COUNT = 3;

    private final List<Entity> mEntities = new ArrayList<Entity>() {
        {
            // This implementation is just for demo purpose.
            for (long i = 1; i < 10; i++) {
                add(new Entity(i, "Label " + i));
            }
        }
    };

    private final Set<Entity> mChecked = new HashSet<>();

    public int getCount() {
        return mEntities.size();
    }

    public Entity getItem(int position) {
        return mEntities.get(position);
    }

    public long getItemId(int position) {
        return getItem(position).getId();
    }

    public int getCheckedCount() {
        return mChecked.size();
    }

    public boolean isChecked(int position) {
        return isChecked(mEntities.get(position));
    }

    public boolean isChecked(Entity entity) {
        return mChecked.contains(entity);
    }

    public void setChecked(int position, boolean checked) {
        Entity entity = mEntities.get(position);
        setChecked(entity, checked);
    }

    /**
     * Change the checked state of the specific element.
     * Checking that the number of elements checked don't exceed MAX_CHECKED_COUNT is responsibility of a caller.
     *
     * @param entity Which element should be changed
     * @param checked New checked state
     * @throws IllegalStateException Throws if the number of elements checked exceeds MAX_CHECKED_COUNT
     */
    public void setChecked(Entity entity, boolean checked) {
        if (checked) {
            if (mChecked.contains(entity)) {
                return;
            }
            if (mChecked.size() + 1 <= MAX_CHECKED_COUNT) {
                mChecked.add(entity);
            } else {
                throw new IllegalStateException("Checked count is exceed by MAX_CHECKED_COUNT(=" + MAX_CHECKED_COUNT + ")");
            }
        } else {
            mChecked.remove(entity);
        }
    }

    public static int getMaxCheckedCount() {
        return MAX_CHECKED_COUNT;
    }

    public boolean isCheckable(Entity item) {
        return mChecked.contains(item) || getCheckedCount() + 1 > MyModel.getMaxCheckedCount();
    }

    public static class Entity {
        private final long mId;
        private final String mMessage;

        public Entity(long id, String message) {
            mId = id;
            mMessage = message;
        }

        public long getId() {
            return mId;
        }

        public String getMessage() {
            return mMessage;
        }
    }
}
