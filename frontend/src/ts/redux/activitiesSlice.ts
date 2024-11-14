import {createSlice, PayloadAction} from '@reduxjs/toolkit';

interface ActivitiesState {
    byId: { [key: number]: Activity }
}

export interface Activity {
    id: number;
    description: string;
}

const initialState: ActivitiesState = {
    byId: {}
}

const activitiesSlice = createSlice({
    name: 'activitiesState',
    initialState,
    reducers: {
        load: function (state, action) {
            let payload = action.payload as Activity[];
            payload.forEach((activity) => {
                state.byId[activity.id] = activity
            })
        },
        clear: function (state, action) {
            state.byId = []
        }
    },
});

export const {load, clear} = activitiesSlice.actions;

export default activitiesSlice;