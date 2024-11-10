import React from "react";
import classNames from "classnames";
import {useAppDispatch, useAppSelector} from "./redux/store";

interface AppProps {
}

const App: React.FC<AppProps> = (props: AppProps) => {

    let dispatch = useAppDispatch();
    let activities = useAppSelector(state => state.activities);
    let suppliers = useAppSelector(state => state.suppliers);

    return <div className="container">
        {generateItems()}
    </div>
}

function generateItems() {
    return Array.from({length: 50}, (v, k) => k)
        .map(function (value) {
            const classnames = classNames({
                'item': true,
                'even': value % 2 == 0,
                'odd': value % 2 == 1,
            });
            return <div className={classnames}>{`ITEM ${value}`}</div>;
        })
}

export default App