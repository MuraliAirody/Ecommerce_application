import React, { useState } from "react";
import HomeSectionCard from "../Home_Section_Crad/HomeSectionCard";
import AliceCarousel from "react-alice-carousel";
import ArrowBackIosNewIcon from "@mui/icons-material/ArrowBackIosNew";
import { Button } from "@mui/material";
import { mens_kurta } from "../../../Data/Mens/mens_kurta";
import "react-alice-carousel/lib/alice-carousel.css";

function HomeSectionsCarousel() {
  const [activeIndex, setActiveIndex] = useState(0);

  const responsive = {
    0: { items: 2 },
    568: { items: 3 },
    1024: { items: 5 },
  };

  const slidePrev = () => {
    console.log("Previous button clicked");

    setActiveIndex(activeIndex - 1);
    // console.log(activeIndex);
  };
  const slideNext = () => {
    console.log("Next button clicked");

    setActiveIndex(activeIndex + 1);
    // console.log(activeIndex);
  };

  const syncActiveIndex = (e) => {
    console.log(e);
    setActiveIndex(e.item);
  };



  const items = mens_kurta
    .slice(0, 10)
    .map((item) => <HomeSectionCard product={item} />);

  return (
    <div className="relative">
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
            console.log(e)
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
              sx={{ color: "black", transform: "rotate(90deg)" }}
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
