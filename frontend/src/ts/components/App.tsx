import React, {useEffect} from "react";
import "../../css/styles.css"
import {useAppDispatch, useAppSelector} from "../redux/store";
import {load} from "../redux/activitiesSlice";

const App: React.FC = () => {

    let dispatch = useAppDispatch();

    let activities = useAppSelector(state => {
        return state.activitiesState.byId
    },);
    let suppliers = useAppSelector(state => state.suppliersState.suppliers);

    useEffect(() => {
        let activities = Array.from(
            {length: 5},
            (_, k) => ({id: k, description: "desc"}));
        dispatch(load(activities))
    }, [])

    return <div className="container">
        {Object.keys(activities).map((id) => {
            return <div className="item" key={id}>{id + " " + activities[Number(id)].description}</div>
        })}
    </div>
}

export default App