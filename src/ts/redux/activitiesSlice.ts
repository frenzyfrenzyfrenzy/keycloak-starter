import {ActionCreatorWithPayload, CaseReducerActions, createSlice, PayloadAction} from '@reduxjs/toolkit';

interface ActivitiesState {
    activities: Activity[]
}

interface Activity {
    id: number;
    description: string;
}

const initialState: ActivitiesState = {
    activities: []
}

const activitiesSlice = createSlice({
    name: 'activities',
    initialState,
    reducers: {
        load: function (state, action: PayloadAction<Activity[]>) {
            return {...state, activities: action.payload}
        },
        clear: function (state, action) {
            return initialState
        }
    },
});

export const {load, clear} = activitiesSlice.actions;

export default activitiesSlice;