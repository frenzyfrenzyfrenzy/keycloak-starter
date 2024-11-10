import React from "react";

interface TileProps {

}

const Tile: React.FC<TileProps> = props => {



    return <div className="tile">
        <img className="image" src="https://picsum.photos/200/300" alt="tile"/>
    </div>

}