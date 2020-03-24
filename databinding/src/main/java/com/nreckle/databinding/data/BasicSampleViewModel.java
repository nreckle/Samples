package com.nreckle.databinding.data;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class BasicSampleViewModel extends ViewModel {
    private final MutableLiveData<String> _firstName = new MutableLiveData<>();
    private final MutableLiveData<String> _lastName = new MutableLiveData<>();
    private final MutableLiveData<Integer> _likes = new MutableLiveData<>();

    public final LiveData<String> firstName = _firstName;
    public final LiveData<String> lastName = _lastName;
    public final LiveData<Integer> likes = _likes;

    public final LiveData<Popularity> popularity = Transformations.map(_likes,
            new Function<Integer, Popularity>() {
                @Override
                public Popularity apply(Integer it) {
                    if (it > 9)
                        return Popularity.STAR;
                    else if (it > 4)
                        return Popularity.POPULAR;
                    else
                        return Popularity.NORMAL;
                }
            });

    {
        _firstName.setValue("Kobe");
        _lastName.setValue("Been");
        _likes.setValue(0);
    }

    public void addLike() {
        _likes.setValue((_likes.getValue() != null) ? (_likes.getValue() + 1) : 1);
    }
}
