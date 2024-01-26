import React, { useState } from "react";
import HomeSectionCard from "../Home_Section_Crad/HomeSectionCard";
import AliceCarousel from "react-alice-carousel";
import ArrowBackIosNewIcon from "@mui/icons-material/ArrowBackIosNew";
import { Button } from "@mui/material";
import "react-alice-carousel/lib/alice-carousel.css";

function HomeSectionsCarousel({data,sectionName}) {
  const [activeIndex, setActiveIndex] = useState(0);

  const responsive = {
    0: { items: 1 },
    500: { items: 2},
    830: { items: 3 },
    1024: { items: 5 },
  };

  const slidePrev = () => {
    // console.log("Previous button clicked");

    setActiveIndex(activeIndex - 1);
    // console.log(activeIndex);
  };
  const slideNext = () => {
    // console.log("Next button clicked");

    setActiveIndex(activeIndex + 1);
    // console.log(activeIndex);
  };

  const syncActiveIndex = (e) => {
    // console.log(e);
    setActiveIndex(e.item);
  };



  const items = data
    .slice(0, 10)
    .map((item,index) => <HomeSectionCard product={item} key={index} />);

  return (
    <div className="relative">
      <h2 className="text-2xl font-extrabold text-gray-950 py-5">{sectionName}</h2>
      <div className="relative p-5">
        <AliceCarousel
        
          items={items}
          disableDotsControls
          // disableButtonsControls
          infinite
          responsive={responsive}
          activeIndex={activeIndex}
          onSlideChanged={(e) => {
            syncActiveIndex(e)
            // console.log(e)
        }}
        autoPlay
        autoPlayInterval={2000}
        />
        
          <Button
            onClick={slideNext}
            variant="contained"
            className="z-50"
            sx={{
              position: "absolute",
              top: "8rem",
              right: "0rem",
              transform: "translate(50%) rotate(90deg)",
              bgcolor: "white",
            }}
            aria-label="next"
          >
            <ArrowBackIosNewIcon
              sx={{ color: "black", transform: "rotate(90deg)",zIndex:"-10px" }}
            />
          </Button>
      
          <Button
            onClick={slidePrev}
            variant="contained"
            className="z-50"
            sx={{
              position: "absolute",
              top: "8rem",
              left: "0rem",
              transform: "translate(-50%) rotate(-90deg)",
              bgcolor: "white",
            }}
            aria-label="next"
          >
            <ArrowBackIosNewIcon
              sx={{ color: "black", transform: "rotate(90deg)" }}
            />
          </Button>
        
      </div>
    </div>
  );
}

export default HomeSectionsCarousel;
